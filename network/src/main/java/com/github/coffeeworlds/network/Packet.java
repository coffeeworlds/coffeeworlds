package com.github.coffeeworlds.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Packet {
  public PacketHeader header;
  public ArrayList<NetMessage> messages;

  public Packet(PacketHeader header) {
    this.header = header;
    this.messages = new ArrayList<NetMessage>();
  }

  public void addMsg(NetMessage msg) {
    this.messages.add(msg);
  }

  public int numMessages() {
    return this.messages.size();
  }

  public byte[] pack() {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    try {
      stream.write(this.header.pack());
      for (NetMessage msg : this.messages) {
        stream.write(msg.pack());
      }
    } catch (IOException ex) {
      System.out.println("packet pack failed: " + ex.getMessage());
      ex.printStackTrace();
    }
    return stream.toByteArray();
  }
}
