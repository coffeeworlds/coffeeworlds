package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import com.github.coffeeworlds.network.system.MsgInfo;
import java.util.HexFormat;
import org.junit.jupiter.api.Test;

class PacketTest {
  @Test
  void pack() {
    PacketHeader header = new PacketHeaderBuilder().buildHeader();
    Packet packet = new Packet(header);

    Chunk chunk = new Chunk(new ChunkHeader(0), new MsgInfo());
    packet.addMessage(chunk);

    byte[] data = packet.pack();
    assertEquals(0x00, data[0]);

    // this is not verified
    byte[] expected =
        new byte[] {
          0, 0, 0, -1, -1, -1, -1, 64, 25, 0, 3, 48, 46, 55, 32, 56, 48, 50, 102, 49, 98, 101, 54,
          48, 97, 48, 53, 54, 54, 53, 102, 0, 0, -123, 28
        };
    assertArrayEquals(expected, data);
  }

  @Test
  void unpack() {
    byte[] data =
        HexFormat.ofDelimiter(" ")
            .parseHex(
                // packet header
                "00 00 01 26 3e 5a 37 "
                    + "40 28 01 03 30 2e 37 20 38 30 32 66 31 62 65 36 30 61 "
                    + "30 35 36 36 35 66 00 6d 79 5f 70 61 73 73 77 6f 72 64 "
                    + "5f 31 32 33 00 85 1c");
    Session session = new Session();
    Packet packet = new Packet(data, session);
    assertEquals(false, packet.header.flags.control);
    assertEquals(false, packet.header.flags.compression);
    assertEquals(false, packet.header.flags.resend);
    assertEquals(false, packet.header.flags.connless);
  }
}
