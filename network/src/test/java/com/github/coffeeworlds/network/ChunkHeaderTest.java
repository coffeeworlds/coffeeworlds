package com.github.coffeeworlds.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChunkHeaderTest {
  @Test
  void basicTwoNullBytes() {
    Packer packer = new Packer();
    packer.addInt(0);
    packer.addInt(0);
    Unpacker unpacker = new Unpacker(packer.data());
    ChunkHeader header = new ChunkHeader(unpacker);
    assertEquals(false, header.flags.vital);
  }

  @Test
  void basic() {
    ChunkHeader header = new ChunkHeader(new byte[] {0x40, 0x3a, 0x01});
    assertEquals(true, header.flags.vital);
    assertEquals(false, header.flags.resend);
    assertEquals(58, header.size);
    assertEquals(1, header.seq);
  }
}
