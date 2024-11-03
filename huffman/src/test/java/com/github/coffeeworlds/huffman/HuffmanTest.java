package com.github.coffeeworlds.huffman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanTest {
  @Test
  void decompressA() {
    Huffman huffman = new Huffman();
    assertArrayEquals(new byte[] {'A'}, huffman.decompress(new byte[] {(byte) 188, 21, 55, 0}));
  }

  @Test
  void decompressAABB() {
    Huffman huffman = new Huffman();
    byte[] data =
        new byte[] {
          (byte) 0xbc, (byte) 0x79, (byte) 0x6b, (byte) 0xa5, (byte) 0x95, (byte) 0xe2, (byte) 0x06
        };
    assertArrayEquals(new byte[] {'A', 'A', 'B', 'B'}, huffman.decompress(data));
  }
}
