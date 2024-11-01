package com.github.coffeeworlds.client;

import com.github.coffeeworlds.network.ControlMessage;
import com.github.coffeeworlds.network.Session;
import com.github.coffeeworlds.network.TeeworldsClient;
import java.util.HexFormat;

public class GameClient {
  byte[] serverToken;
  byte[] clientToken;
  NetClient netClient;
  Session session;
  TeeworldsClient client;

  GameClient() {
    this.session = new Session();
    this.netClient = new NetClient(this);
    this.client = new TeeworldsClient(this.netClient);
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
    System.out.println("TODO: send version and password");
  }

  public void onTick() {
    System.out.print(".");
  }

  public void onNetworkData(byte[] data) {
    String hex = HexFormat.of().withUpperCase().formatHex(data);
    System.out.println(hex);
    System.out.println();

    // control message
    if (data[0] == 0x04) {
      int ctrlMsg = data[7];
      System.out.println("got ctrl msg: " + ctrlMsg);
      // ctrl token
      if (ctrlMsg == 0x05) {
        this.session.peerToken[0] = data[8];
        this.session.peerToken[1] = data[9];
        this.session.peerToken[2] = data[10];
        this.session.peerToken[3] = data[11];

        sendCtrlConnect();
      } else if (ctrlMsg == 0x02) { // accpet
        sendVersionAndPassword();
      }
    } else { // game message
    }
  }
}
