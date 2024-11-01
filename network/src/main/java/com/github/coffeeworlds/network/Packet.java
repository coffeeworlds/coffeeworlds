package com.github.coffeeworlds.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Packet {
  public PacketHeader header;
  public ArrayList<Chunk> messages;

  public Packet(PacketHeader header) {
    this.header = header;
    this.messages = new ArrayList<Chunk>();
  }

  public Packet(byte[] data) {
    this.header = new PacketHeader();
    this.messages = new ArrayList<Chunk>();
    unpack(data);
  }

  @Override
  public String toString() {
    return String.format("<Packet header=%s>", this.header);
  }

  public void addMessage(Chunk chunk) {
    this.messages.add(chunk);
  }

  public int numMessages() {
    return this.messages.size();
  }

  public void unpack(byte[] data) {
    this.header.unpack(data);
  }

  public byte[] pack() {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    try {
      stream.write(this.header.pack());
      for (Chunk chunk : this.messages) {
        byte[] msgPayload = chunk.message.pack();
        if (chunk.header.size == -1) {
          chunk.header.size = msgPayload.length;
        }
        stream.write(chunk.header.pack());
        stream.write(msgPayload);
      }
    } catch (IOException ex) {
      System.out.println("packet pack failed: " + ex.getMessage());
      ex.printStackTrace();
    }
    return stream.toByteArray();
  }
}
