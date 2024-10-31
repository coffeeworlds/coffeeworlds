package com.github.coffeeworlds.client;

import com.github.coffeeworlds.network.UdpClient;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class NetClient implements UdpClient {
  DatagramSocket socket;
  InetAddress serverAddress;
  int serverPort;
  GameClient gameClient;

  public NetClient(GameClient gameClient) {
    // there is one network read thread that will be reused
    // if we connect to a different server
    // and we never cleanly shut it down lol
    // even if we would signal a shutdown flag to it
    // its blocked on the read so it would be unreliable
    networkReadThread(this);
    this.gameClient = gameClient;
  }

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

  static Thread networkReadThread(NetClient client) {
    return Thread.ofVirtual()
        .name("network-read")
        .start(
            () -> {
              while (true) {
                // if we are not connected to a server yet
                if (client.socket == null) {
                  // this sleep is to avoid cpu spin before connecting to the server
                  // also fixes not being able to receive anything
                  // i assume a while true continue freezes the thread scheduler
                  try {
                    Thread.sleep(100);
                  } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt(); // set interrupt flag
                    System.out.println("Failed to compute sum");
                  }
                  continue;
                }

                try {
                  byte[] buffer = new byte[512];
                  DatagramPacket response = new DatagramPacket(buffer, buffer.length);

                  client.socket.receive(response);

                  // creating a copy just to shrink the array sounds nuts
                  // maybe we should pass the size along instead
                  byte[] serverData = Arrays.copyOfRange(buffer, 0, response.getLength());
                  client.onPacket(serverData);

                } catch (SocketTimeoutException ex) {
                  System.out.println("Timeout error: " + ex.getMessage());
                  ex.printStackTrace();
                } catch (IOException ex) {
                  System.out.println("IO error: " + ex.getMessage());
                  ex.printStackTrace();
                }
              }
            });
  }

  public void send(byte[] data) {
    try {
      DatagramPacket request =
          new DatagramPacket(data, data.length, this.serverAddress, this.serverPort);
      socket.send(request);
    } catch (IOException ex) {
      System.out.println("IO error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  void onPacket(byte[] data) {
    this.gameClient.onNetworkData(data);
  }
}
