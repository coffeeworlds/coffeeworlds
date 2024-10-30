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

  Thread readThread;
  // used to kill the network thread
  boolean running = true;

  public NetClient() {
    // there is one network read thread that will be reused
    // if we connect to a different server
    this.readThread = networkReadThread(this);
  }

  public void shutdown() {
    this.running = false;
    System.out.println("net client shutting down ...");
    try {
      this.readThread.join();
    } catch (InterruptedException ex) {
      System.out.println("net client shutdown interrupted!");
    }
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

  static Thread networkReadThread(NetClient client) {
    return Thread.ofVirtual()
        .name("network-read")
        .start(
            () -> {
              while (client.running) {
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

  public void pumpNetwork() {
    System.out.print(".");

    try {
      byte[] data = ctrlConnect();
      DatagramPacket request =
          new DatagramPacket(data, data.length, this.serverAddress, this.serverPort);
      socket.send(request);
    } catch (IOException ex) {
      System.out.println("IO error: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  void onPacket(byte[] data) {
    String hex = HexFormat.of().withUpperCase().formatHex(data);
    System.out.println(hex);
    System.out.println();
  }
}
