package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PacketTest {
  @Test
  void basic() {
    Packet packet = new Packet();
    assertEquals(0, packet.numMessages());
  }
}
