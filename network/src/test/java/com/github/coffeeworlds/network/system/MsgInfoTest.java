package com.github.coffeeworlds.network.system;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MsgInfoTest {
  @Test
  void msgIdAndSystemFlag() {
    MsgInfo msg = new MsgInfo();
    assertEquals(0x03, msg.data()[0]); // msg id and system flag
  }
}
