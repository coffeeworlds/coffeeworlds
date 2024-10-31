package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import com.github.coffeeworlds.network.system.MsgInfo;
import org.junit.jupiter.api.Test;

class PacketTest {
  @Test
  void basic() {
    PacketHeader header = new PacketHeaderBuilder().buildHeader();
    Packet packet = new Packet(header);
    packet.addMsg(new MsgInfo());

    byte[] data = packet.pack();
    assertEquals(0x00, data[0]);

    // TODO: this expected value is wrong lol
    //       its missing num chunks and chunk header
    byte[] expected =
        new byte[] {
          0, 0, 0, -1, -1, -1, -1, 3, 48, 46, 55, 32, 56, 48, 50, 102, 49, 98, 101, 54, 48, 97, 48,
          53, 54, 54, 53, 102, 0, 0, -123, 28
        };
    assertArrayEquals(expected, data);
  }
}
