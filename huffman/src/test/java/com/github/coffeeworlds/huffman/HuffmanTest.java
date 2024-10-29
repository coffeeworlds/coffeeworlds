package com.github.coffeeworlds.huffman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanTest {
  @Test
  void basicDecompress() {
    Huffman huffman = new Huffman();
    assertArrayEquals(new byte[] {0x00}, huffman.decompress(new byte[] {0x00}));
  }
}
