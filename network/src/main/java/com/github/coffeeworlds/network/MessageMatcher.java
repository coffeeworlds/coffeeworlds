package com.github.coffeeworlds.network;

import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.system.MsgConReady;
import com.github.coffeeworlds.network.system.MsgInfo;
import com.github.coffeeworlds.network.system.MsgMapChange;
import java.util.ArrayList;
import java.util.HexFormat;

public class MessageMatcher {
  // Will set the ack - the amount of vital chunks received
  private Unpacker unpacker;
  public Session session;
  public ArrayList<Chunk> messages;
  private MessageHandler messageHandler;

  public MessageMatcher(Session session, Unpacker unpacker, MessageHandler handler) {
    this.messages = new ArrayList<Chunk>();
    this.unpacker = unpacker;
    this.session = session;
    this.messageHandler = handler;
  }

  public boolean matchSys(int msgId, ChunkHeader header) {
    if (msgId == SystemMessage.INFO) {
      MsgInfo msg = new MsgInfo(this.unpacker);
      this.messages.add(new Chunk(header, msg));
      return true;
    } else if (msgId == SystemMessage.MAP_CHANGE) {
      MsgMapChange msg = new MsgMapChange(this.unpacker);
      this.messages.add(new Chunk(header, msg));
      this.messageHandler.onMapChange(msg);
    } else if (msgId == SystemMessage.CON_READY) {
      MsgConReady msg = new MsgConReady(this.unpacker);
      this.messages.add(new Chunk(header, msg));
      this.messageHandler.onConReady(msg);
    }
    return false;
  }

  // return true if a message was consumed
  // returns falls if end of data is hit
  public boolean getMessage() throws TeeworldsException {
    if (this.unpacker.remainingSize() == 0) {
      return false;
    }
    if (this.unpacker.remainingSize() < 3) {
      throw new TeeworldsException(
          "Not enough bytes to read another message. Remaining data: "
              + HexFormat.of().formatHex(this.unpacker.getRemainingData()));
    }

    ChunkHeader header = new ChunkHeader(this.unpacker);
    int msgAndSys = this.unpacker.getInt();
    MsgType msgType = ((msgAndSys & 1) == 0) ? MsgType.GAME : MsgType.SYSTEM;
    int msgId = msgAndSys >> 1;

    if (msgType == MsgType.SYSTEM) {
      return matchSys(msgId, header);
    }

    return false;
  }
}
