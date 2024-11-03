package com.github.coffeeworlds.network;

import com.github.coffeeworlds.huffman.Huffman;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HexFormat;

public class Packet {
  public PacketHeader header;
  public ArrayList<Chunk> messages;
  private MessageHandler messageHandler;
  private Huffman huffman;

  public Packet(PacketHeader header) {
    this.header = header;
    this.messages = new ArrayList<Chunk>();
    this.messageHandler = new MessageHandler();
    this.huffman = new Huffman();
  }

  public Packet(byte[] data, Session session) {
    this.header = new PacketHeader();
    this.messages = new ArrayList<Chunk>();
    this.messageHandler = new MessageHandler();
    this.huffman = new Huffman();
    unpack(data, session);
  }

  public Packet(byte[] data, Session session, MessageHandler handler) {
    this.header = new PacketHeader();
    this.messages = new ArrayList<Chunk>();
    this.messageHandler = handler;
    this.huffman = new Huffman();
    unpack(data, session);
  }

  public Packet(byte[] data, Session session, MessageHandler handler, Huffman huffman) {
    this.header = new PacketHeader();
    this.messages = new ArrayList<Chunk>();
    this.messageHandler = handler;
    this.huffman = huffman;
    unpack(data, session);
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

  public void unpack(byte[] data, Session session) {
    this.header.unpack(data);
    Unpacker unpacker = new Unpacker(data);
    // pop header before parsing messages
    unpacker.getRaw(Protocol.NET_PACKETHEADERSIZE);

    if (this.header.flags.control) {
      // the user has to handle those lol
      return;
    }

    if (this.header.flags.compression) {
      byte[] decompressed = this.huffman.decompress(unpacker.getRemainingData());
      unpacker = new Unpacker(decompressed);
    }

    MessageMatcher matcher = new MessageMatcher(session, unpacker, this.messageHandler);
    try {
      while (matcher.getMessage()) {
        // do what now?
      }
    } catch (TeeworldsException ex) {
      System.out.println("!!! full packet: " + HexFormat.of().formatHex(unpacker.getFullData()));
      System.out.println("!!! packet unpack failed: " + ex.getMessage());
      ex.printStackTrace();
    }
    this.messages = matcher.messages;
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
