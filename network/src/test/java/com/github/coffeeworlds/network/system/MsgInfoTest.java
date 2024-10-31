package com.github.coffeeworlds.network.system;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MsgInfoTest {
  @Test
  void msgIdAndSystemFlag() {
    MsgInfo msg = new MsgInfo();
    assertEquals(0x03, msg.pack()[0]); // msg id and system flag
    assertEquals(0x0705, msg.clientVersion);
  }

  @Test
  void useBuilder() {
    MsgInfo msg = new MsgInfoBuilder().netVersion("xxx").buildMsg();
    assertEquals(0x03, msg.pack()[0]); // msg id and system flag
    assertEquals('x', msg.pack()[1]); // custom net version
  }

  @Test
  void unpack() {
    MsgInfo msg = new MsgInfo(new byte[] {'x', 0x00, 0x00, 0x02});
    assertEquals("x", msg.netVersion);
    assertEquals("", msg.password);
    assertEquals(0x02, msg.clientVersion);
  }

  @Test
  void repack() {
    MsgInfo msg =
        new MsgInfo(
            new byte[] {
              '0', '.', '7', ' ', '2', '2', 'c', 0x00, 'a', 'b', 'c', 0x00, (byte) 0x0705
            });
    assertEquals("0.7 22c", msg.netVersion);
    assertEquals("abc", msg.password);
    assertEquals((byte) 0x0705, msg.clientVersion);

    msg.netVersion = "x";
    assertEquals("x", msg.netVersion);
    assertEquals('x', msg.pack()[1]); // 0 is msg id
  }
}
