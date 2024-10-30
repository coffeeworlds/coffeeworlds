package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PackerTest {
  @Test
  void basicString() {
    Packer packer = new Packer();
    packer.addString("ABC");
    assertEquals(4, packer.size());
    assertArrayEquals(new byte[] {'A', 'B', 'C', 0x00}, packer.data());
    packer.addString("XX");
    assertArrayEquals(new byte[] {'A', 'B', 'C', 0x00, 'X', 'X', 0x00}, packer.data());
  }
}
