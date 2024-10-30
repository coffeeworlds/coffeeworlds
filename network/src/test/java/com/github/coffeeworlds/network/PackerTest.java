package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PackerTest {
  @Test
  void basicInt() {
    Packer packer = new Packer();
    packer.addInt(0);
    assertEquals(1, packer.size());
    assertArrayEquals(new byte[] {0x00}, packer.data());
    packer.addInt(1);
    assertArrayEquals(new byte[] {0x00, 0x01}, packer.data());
  }

  @Test
  void multiByteInt() {
    Packer packer = new Packer();
    packer.addInt(64);
    assertEquals(2, packer.size());
    assertArrayEquals(new byte[] {(byte) 0x80, 0x01}, packer.data());
    packer.addInt(65);
    assertArrayEquals(new byte[] {(byte) 0x80, 0x01, (byte) 0x81, 0x01}, packer.data());
  }

  @Test
  void negativeInt() {
    Packer packer = new Packer();
    packer.addInt(-63);
    assertEquals(1, packer.size());
    assertArrayEquals(new byte[] {(byte) 0x7e}, packer.data());
    packer.addInt(-64);
    assertArrayEquals(new byte[] {(byte) 0x7e, 0x7f}, packer.data());
    packer.addInt(-65);
    assertArrayEquals(new byte[] {(byte) 0x7e, 0x7f, (byte) 0xc0, 0x1}, packer.data());
  }

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
