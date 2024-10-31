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
}
