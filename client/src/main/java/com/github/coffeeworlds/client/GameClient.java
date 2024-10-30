package com.github.coffeeworlds.client;

import java.util.HexFormat;

public class GameClient {
  byte[] serverToken;
  byte[] clientToken;
  NetClient netClient;

  GameClient() {
    this.serverToken = new byte[] {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
    // this should be random xd
    this.clientToken = new byte[] {0x01, 0x01, 0x02, 0x01};

    this.netClient = new NetClient(this);
  }

  public void connect(String serverIp, int serverPort) {
    this.netClient.connect(serverIp, serverPort);
    this.netClient.sendData(ctrlToken());
  }

  public void disconnect() {
    this.netClient.disconnect();
  }

  public byte[] ctrlToken() {
    byte[] data = new byte[600];
    data[0] = 0x04;
    data[1] = 0x00;
    data[2] = 0x00;
    data[3] = (byte) 0xff;
    data[4] = (byte) 0xff;
    data[5] = (byte) 0xff;
    data[6] = (byte) 0xff;
    data[7] = 0x05; // ctrl connect
    data[8] = this.clientToken[0];
    data[9] = this.clientToken[1];
    data[10] = this.clientToken[2];
    data[11] = this.clientToken[3];

    return data;
  }

  public void sendCtrlConnect() {
    byte[] data = new byte[600]; // this is too much

    // yes I do not know java. What gave it away?
    data[0] = 0x04;
    data[1] = 0x00;
    data[2] = 0x00;
    data[3] = this.serverToken[0];
    data[4] = this.serverToken[1];
    data[5] = this.serverToken[2];
    data[6] = this.serverToken[3];
    data[7] = 0x01;
    data[8] = this.clientToken[0];
    data[9] = this.clientToken[1];
    data[10] = this.clientToken[2];
    data[11] = this.clientToken[3];

    this.netClient.sendData(data);
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
        this.serverToken[0] = data[8];
        this.serverToken[1] = data[9];
        this.serverToken[2] = data[10];
        this.serverToken[3] = data[11];

        sendCtrlConnect();
      } else if (ctrlMsg == 0x02) { // accpet
        sendVersionAndPassword();
      }
    } else { // game message
    }
  }
}
