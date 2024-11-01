package com.github.coffeeworlds.network;

import java.util.ArrayList;
import java.util.Arrays;

// the protocol state machine
public class TeeworldsClient {
  private ArrayList<Chunk> messages;
  private UdpClient udpClient;
  public Session session;

  public TeeworldsClient(Session session, UdpClient udpClient) {
    this.session = session;
    this.udpClient = udpClient;
    this.messages = new ArrayList<Chunk>();
  }

  public TeeworldsClient(UdpClient udpClient) {
    this.session = new Session();
    this.udpClient = udpClient;
    this.messages = new ArrayList<Chunk>();
  }

  // queues a message
  public void sendMessage(NetMessage msg) {
    ChunkFlags flags = new ChunkFlags();
    flags.vital = true;
    sendMessage(msg, flags);
  }

  // queues a message
  public void sendNonVitalMessage(NetMessage msg) {
    ChunkFlags flags = new ChunkFlags();
    sendMessage(msg, flags);
  }

  // queues a message
  public void sendMessage(NetMessage msg, ChunkFlags flags) {
    if (flags.vital) {
      session.sequence++;
    }
    ChunkHeader header = new ChunkHeader(-1, session.sequence, flags);
    Chunk chunk = new Chunk(header, msg);
    this.messages.add(chunk);
  }

  public void sendCtrlToken(byte[] token) {
    byte[] payload = Arrays.copyOf(token, Protocol.REFLECTION_PROTECTION_PAYLOAD_SIZE);
    sendCtrlMsg(ControlMessage.TOKEN, payload);
  }

  public void sendCtrlConnect(byte[] token) {
    byte[] payload = Arrays.copyOf(token, Protocol.REFLECTION_PROTECTION_PAYLOAD_SIZE);
    sendCtrlMsg(ControlMessage.CONNECT, payload);
  }

  public void sendCtrlMsg(int MsgId) {
    sendCtrlMsg(MsgId, new byte[0]);
  }

  public void sendCtrlMsg(int MsgId, byte[] payload) {
    // make sure all previously queued game/sys messages get sent first
    flush();

    byte[] header =
        new PacketHeaderBuilder()
            .ack(this.session.ack)
            .token(this.session.peerToken)
            .setFlagControl()
            .buildHeader()
            .pack();

    if (payload.length == 0) {
      this.udpClient.send(header);
      return;
    }

    byte[] data = Arrays.copyOf(header, header.length + payload.length + 1);
    data[7] = (byte) MsgId;
    System.arraycopy(payload, 0, data, header.length + 1, payload.length);
    this.udpClient.send(data);
  }

  // TODO: check sizes here
  //       auto flush if we reached max packet size
  //       send multiple packets if they cant fit
  public void flush() {
    if (this.messages.size() == 0) {
      return;
    }

    PacketHeader header =
        new PacketHeaderBuilder()
            .ack(this.session.ack)
            .numChunks(this.messages.size())
            .token(this.session.peerToken)
            .buildHeader();
    Packet packet = new Packet(header);
    packet.messages = this.messages;
    this.udpClient.send(packet.pack());
    this.messages.clear();
  }
}
