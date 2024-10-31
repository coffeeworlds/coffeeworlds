package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PackerHeaderBuilderTest {
  @Test
  void basic() {
    PacketHeader header =
        new PacketHeaderBuilder()
            .ack(0)
            .numChunks(2)
            .token(new byte[] {0x0c, 0x0c, 0x0c, 0x0c})
            .setFlagControl()
            .buildHeader();

    assertEquals(0x04, header.pack()[0]);
    assertEquals(0x00, header.pack()[1]);
    assertEquals(0x02, header.pack()[2]);
    assertEquals(0x0c, header.pack()[3]);
    assertEquals(0x0c, header.pack()[4]);
    assertEquals(0x0c, header.pack()[5]);
    assertEquals(0x0c, header.pack()[6]);
  }
}
