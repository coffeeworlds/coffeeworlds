package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PackerHeaderBuilderTest {
  @Test
  void packAndRepack() {
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

    header.token = new byte[] {0x05, 0x05, 0x05, 0x05};
    assertEquals(0x04, header.pack()[0]);
    assertEquals(0x00, header.pack()[1]);
    assertEquals(0x02, header.pack()[2]);
    assertEquals(0x05, header.pack()[3]);
    assertEquals(0x05, header.pack()[4]);
    assertEquals(0x05, header.pack()[5]);
    assertEquals(0x05, header.pack()[6]);
  }

  @Test
  void unpack() {
    byte[] data = new byte[] {0x04, 0x00, 0x02, 0x0c, 0x0c, 0x0c, 0x0c};
    PacketHeader header = new PacketHeader(data);
    assertEquals(true, header.flags.control);
    assertEquals(false, header.flags.resend);
    assertEquals(false, header.flags.compression);
    assertEquals(false, header.flags.connless);
    assertEquals(0, header.ack);
    assertEquals(2, header.numChunks);
    assertArrayEquals(new byte[] {0x0c, 0x0c, 0x0c, 0x0c}, header.token);
  }
}
