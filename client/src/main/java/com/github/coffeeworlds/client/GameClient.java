package com.github.coffeeworlds.client;

import com.github.coffeeworlds.network.Chunk;
import com.github.coffeeworlds.network.ControlMessage;
import com.github.coffeeworlds.network.MessageHandler;
import com.github.coffeeworlds.network.Packet;
import com.github.coffeeworlds.network.Protocol;
import com.github.coffeeworlds.network.Session;
import com.github.coffeeworlds.network.TeeworldsClient;
import com.github.coffeeworlds.network.Unpacker;
import com.github.coffeeworlds.network.game.MsgClStartInfo;
import com.github.coffeeworlds.network.game.MsgClStartInfoBuilder;
import com.github.coffeeworlds.network.system.MsgConReady;
import com.github.coffeeworlds.network.system.MsgInfo;
import com.github.coffeeworlds.network.system.MsgInfoBuilder;
import com.github.coffeeworlds.network.system.MsgMapChange;
import com.github.coffeeworlds.network.system.MsgReady;
import java.util.HexFormat;

public class GameClient extends MessageHandler {
  byte[] serverToken;
  byte[] clientToken;
  NetClient netClient;
  Session session;
  TeeworldsClient client;

  GameClient() {
    this.session = new Session();
    this.netClient = new NetClient(this);
    this.client = new TeeworldsClient(this.session, this.netClient);
  }

  public void connect(String serverIp, int serverPort) {
    this.netClient.connect(serverIp, serverPort);
    sendCtrlToken();
  }

  public void disconnect() {
    this.client.sendCtrlMsg(ControlMessage.CLOSE);
  }

  public void sendCtrlToken() {
    this.client.sendCtrlToken(this.session.token);
  }

  public void sendCtrlConnect() {
    this.client.sendCtrlConnect(this.session.token);
  }

  public void sendVersionAndPassword() {
    MsgInfo msg = new MsgInfoBuilder().password("").buildMsg();
    this.client.sendMessage(msg);
  }

  public void sendReady() {
    MsgReady msg = new MsgReady();
    this.client.sendMessage(msg);
  }

  @Override
  public void onMapChange(MsgMapChange msg) {
    super.onMapChange(msg);
    sendReady();
  }

  @Override
  public void onConReady(MsgConReady msg) {
    super.onConReady(msg);

    MsgClStartInfo info = new MsgClStartInfoBuilder().name("coffewrlds.jar").buildMsg();
    this.client.sendMessage(info);
  }

  public void onTick() {
    // System.out.print(".");

    // TODO: when do we flush?
    this.client.flush();
  }

  // TODO: this is called directly from the thread
  //       have onTick() check if there is new data and then consume it in the main thread
  //       to avoid any race conditions
  public void onNetworkData(byte[] data) {
    Packet packet = new Packet(data, this.session, this);
    System.out.println(packet);

    // control message
    if (packet.header.flags.control) {
      Unpacker unpacker = new Unpacker(data);
      unpacker.getRaw(Protocol.NET_PACKETHEADERSIZE);
      int ctrlMsg = unpacker.getInt();
      System.out.println("got ctrl msg: " + ctrlMsg);
      if (ctrlMsg == ControlMessage.TOKEN) {
        this.session.peerToken[0] = data[8];
        this.session.peerToken[1] = data[9];
        this.session.peerToken[2] = data[10];
        this.session.peerToken[3] = data[11];

        sendCtrlConnect();
      } else if (ctrlMsg == ControlMessage.ACCEPT) {
        System.out.println("got accpet");
        sendVersionAndPassword();
      } else if (ctrlMsg == ControlMessage.CLOSE) {
        System.out.println("got close from server");
        System.exit(0);
      } else if (ctrlMsg == ControlMessage.KEEPALIVE) {
        // silently ignore keepalives
      } else {
        System.out.println("unknown control message: " + ctrlMsg);
        String hex = HexFormat.of().withUpperCase().formatHex(data);
        System.out.println(hex);
        System.out.println();
      }
    } else { // game or sys message
      for (Chunk msg : packet.messages) {
        System.out.println("got msg: " + msg.message.name());
      }
    }
  }
}
