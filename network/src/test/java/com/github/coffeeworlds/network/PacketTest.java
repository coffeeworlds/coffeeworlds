package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import com.github.coffeeworlds.network.system.MsgInfo;
import org.junit.jupiter.api.Test;

class PacketTest {
  @Test
  void basic() {
    PacketHeader header = new PacketHeaderBuilder().buildHeader();
    Packet packet = new Packet(header);

    Chunk chunk = new Chunk(new ChunkHeader(0), new MsgInfo());
    packet.addMessage(chunk);

    byte[] data = packet.pack();
    assertEquals(0x00, data[0]);

    // this is not verified
    byte[] expected =
        new byte[] {
          0, 0, 0, -1, -1, -1, -1, 64, 25, 25, 3, 48, 46, 55, 32, 56, 48, 50, 102, 49, 98, 101, 54,
          48, 97, 48, 53, 54, 54, 53, 102, 0, 0, -123, 28
        };
    assertArrayEquals(expected, data);
  }
}
