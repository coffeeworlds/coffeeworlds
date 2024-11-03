package com.github.coffeeworlds.huffman;

class ConstructionNode {
  public int nodeId;
  public int frequency;

  public ConstructionNode(int nodeId, int frequency) {
    this.nodeId = nodeId;
    this.frequency = frequency;
  }

  public ConstructionNode() {
    this.nodeId = 0;
    this.frequency = 0;
  }
}
