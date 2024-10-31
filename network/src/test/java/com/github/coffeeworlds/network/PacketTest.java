package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PacketTest {
  @Test
  void basic() {
    PacketHeader header = new PacketHeaderBuilder().buildHeader();
    Packet packet = new Packet(header);
    assertEquals(0, packet.numMessages());
  }
}
