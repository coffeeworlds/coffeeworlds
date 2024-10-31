package com.github.coffeeworlds.network;

public class Packet {
  public PacketHeader header;

  public Packet(PacketHeader header) {
    this.header = header;
  }

  public int numMessages() {
    return 0;
  }
}
