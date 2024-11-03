package com.github.coffeeworlds.huffman;

class Node {
  public int bits;
  public int numBits;
  public int[] leafs;
  public int symbol;

  public Node(int bits, int numBits, int symbol) {
    this.bits = bits;
    this.numBits = numBits;
    this.symbol = symbol;
    this.leafs = new int[2];
  }
}
