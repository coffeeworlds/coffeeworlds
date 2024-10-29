package com.github.coffeeworlds.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HexFormat;

public class NetClient {
  DatagramSocket socket;
  InetAddress serverAddress;
  int serverPort;

  public boolean connect(String serverIp, int serverPort) {
    this.serverPort = serverPort;
    try {
      serverAddress = InetAddress.getByName(serverIp);
      this.socket = new DatagramSocket();
    } catch (SocketException ex) {
      System.out.println("Socket error: " + ex.getMessage());
      ex.printStackTrace();
      return false;
    } catch (UnknownHostException ex) {
      System.out.println("Unknown host error: " + ex.getMessage());
      ex.printStackTrace();
      return false;
    }
    return true;
  }

  public byte[] ctrlConnect() {
    byte[] data = new byte[600];
    data[0] = 0x04;
    data[1] = 0x00;
    data[3] = (byte) 0xff;
    data[4] = (byte) 0xff;
    data[5] = (byte) 0xff;
    data[6] = (byte) 0xff;
    data[7] = 0x05; // ctrl connect
    data[8] = 0x0c;
    data[9] = 0x0c;
    data[10] = 0x0c;
    data[11] = 0x0c;
    return data;
  }

  public void pumpNetwork() {
    try {
      byte[] data = ctrlConnect();
      DatagramPacket request =
          new DatagramPacket(data, data.length, this.serverAddress, this.serverPort);
      socket.send(request);

      byte[] buffer = new byte[512];
      DatagramPacket response = new DatagramPacket(buffer, buffer.length);
      socket.receive(response);

      // creating a copy just to shrink the array sounds nuts
      // maybe we should pass the size along instead
      byte[] serverData = Arrays.copyOfRange(buffer, 0, response.getLength());
      onPacket(serverData);

    } catch (SocketTimeoutException ex) {
      System.out.println("Timeout error: " + ex.getMessage());
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Client error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  void onPacket(byte[] data) {
    String hex = HexFormat.of().withUpperCase().formatHex(data);
    System.out.println(hex);
    System.out.println();
  }
}
