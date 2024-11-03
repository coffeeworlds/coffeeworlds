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

  @Override
  public String toString() {
    return String.format(
        "<Node bits=%d numBits=%d symbol=%d leafs=[%d, %d]>",
        this.bits, this.numBits, this.symbol, this.leafs[0], this.leafs[1]);
  }
}
