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
}
