package com.github.coffeeworlds.huffman;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Comparator;

class SortByFrequencyDesc implements Comparator<ConstructionNode> {
  public int compare(ConstructionNode node1, ConstructionNode node2) {
    if (node2.frequency < node1.frequency) {
      return -1;
    }
    if (node2.frequency > node1.frequency) {
      return 1;
    }
    return 0;
  }
}

public class Huffman {
  public static final int EOF_SYMBOL = 256;
  public static final int MAX_SYMBOLS = EOF_SYMBOL + 1;
  public static final int MAX_NODES = MAX_SYMBOLS * 2 - 1;
  public static final int LUTBITS = 10;
  public static final int LUTSIZE = 1 << LUTBITS;
  public static final int LUTMASK = LUTSIZE - 1;
  public static final int[] FREQUENCY_TABLE =
      new int[] {
        1 << 30, 4545, 2657, 431, 1950, 919, 444, 482, 2244, 617, 838, 542, 715, 1814, 304, 240,
        754, 212, 647, 186, 283, 131, 146, 166, 543, 164, 167, 136, 179, 859, 363, 113, 157, 154,
        204, 108, 137, 180, 202, 176, 872, 404, 168, 134, 151, 111, 113, 109, 120, 126, 129, 100,
        41, 20, 16, 22, 18, 18, 17, 19, 16, 37, 13, 21, 362, 166, 99, 78, 95, 88, 81, 70, 83, 284,
        91, 187, 77, 68, 52, 68, 59, 66, 61, 638, 71, 157, 50, 46, 69, 43, 11, 24, 13, 19, 10, 12,
        12, 20, 14, 9, 20, 20, 10, 10, 15, 15, 12, 12, 7, 19, 15, 14, 13, 18, 35, 19, 17, 14, 8, 5,
        15, 17, 9, 15, 14, 18, 8, 10, 2173, 134, 157, 68, 188, 60, 170, 60, 194, 62, 175, 71, 148,
        67, 167, 78, 211, 67, 156, 69, 1674, 90, 174, 53, 147, 89, 181, 51, 174, 63, 163, 80, 167,
        94, 128, 122, 223, 153, 218, 77, 200, 110, 190, 73, 174, 69, 145, 66, 277, 143, 141, 60,
        136, 53, 180, 57, 142, 57, 158, 61, 166, 112, 152, 92, 26, 22, 21, 28, 20, 26, 30, 21, 32,
        27, 20, 17, 23, 21, 30, 22, 22, 21, 27, 25, 17, 27, 23, 18, 39, 26, 15, 21, 12, 18, 18, 27,
        20, 18, 15, 19, 11, 17, 33, 12, 18, 15, 19, 18, 16, 26, 17, 18, 9, 10, 25, 22, 22, 17, 20,
        16, 6, 16, 15, 20, 14, 18, 24, 335, 1517
      };

  private Node[] nodes;
  private Node[] decodedLut;
  private int numNodes;
  private Node startNode;

  public Huffman() {
    this.nodes = new Node[MAX_NODES];
    this.decodedLut = new Node[LUTSIZE];
    this.numNodes = 0;

    for (int i = 0; i < MAX_NODES; i++) {
      this.nodes[i] = new Node(0, 0, 0);
    }
    for (int i = 0; i < LUTSIZE; i++) {
      this.decodedLut[i] = new Node(0, 0, 0);
    }

    constructTree(FREQUENCY_TABLE);

    for (int i = 0; i < Huffman.LUTSIZE; i++) {
      int bits = i;
      Node node = this.startNode;
      int k = 0;
      while (k < Huffman.LUTBITS) {
        node = this.nodes[node.leafs[bits & 1]];
        bits >>= 1;

        if (node.numBits != 0) {
          this.decodedLut[i] = node;
          break;
        }
        k++;
      }
      if (k == Huffman.LUTBITS) {
        this.decodedLut[i] = node;
      }
    }
  }

  void setbitsR(Node node, int bits, int depth) {
    if (node.leafs[1] != 0xffff) {
      setbitsR(this.nodes[node.leafs[1]], bits | (1 << depth), depth + 1);
    }
    if (node.leafs[0] != 0xffff) {
      setbitsR(this.nodes[node.leafs[0]], bits, depth + 1);
    }

    if (node.numBits != 0) {
      node.bits = bits;
      node.numBits = depth;
    }
  }

  private void constructTree(int[] frequencies) {
    ConstructionNode[] nodesLeft = new ConstructionNode[Huffman.MAX_SYMBOLS];
    int numNodesLeft = Huffman.MAX_SYMBOLS;
    for (int i = 0; i < Huffman.MAX_SYMBOLS; i++) {
      nodesLeft[i] = new ConstructionNode();
    }

    for (int i = 0; i < Huffman.MAX_SYMBOLS; i++) {
      this.nodes[i].numBits = 0xffffffff;
      this.nodes[i].symbol = i;
      this.nodes[i].leafs[0] = 0xffff;
      this.nodes[i].leafs[1] = 0xffff;

      if (i == Huffman.EOF_SYMBOL) {
        nodesLeft[i].frequency = 1;
      } else {
        nodesLeft[i].frequency = frequencies[i];
      }

      nodesLeft[i].nodeId = i;
    }

    this.numNodes = Huffman.MAX_SYMBOLS;

    while (numNodesLeft > 1) {
      // is this stable?
      Arrays.sort(nodesLeft, new SortByFrequencyDesc());

      this.nodes[this.numNodes].numBits = 0;
      this.nodes[this.numNodes].leafs[0] = nodesLeft[numNodesLeft - 1].nodeId;
      this.nodes[this.numNodes].leafs[1] = nodesLeft[numNodesLeft - 2].nodeId;
      nodesLeft[numNodesLeft - 2].nodeId = this.numNodes;
      nodesLeft[numNodesLeft - 2].frequency =
          nodesLeft[numNodesLeft - 1].frequency + nodesLeft[numNodesLeft - 2].frequency;
      this.numNodes++;
      numNodesLeft--;
    }
    this.startNode = this.nodes[this.numNodes - 1];
    setbitsR(this.startNode, 0, 0);
  }

  public byte[] decompress(byte[] data) {
    int srcIndex = 0;
    int size = data.length;
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    int bits = 0;
    int bitcount = 0;
    Node eof = this.nodes[EOF_SYMBOL];
    Node node = new Node(0, 0, 0);

    while (true) {
      boolean foundNode = false;
      if (bitcount >= LUTBITS) {
        node = this.decodedLut[bits & LUTMASK];
        foundNode = true;
      }

      while (bitcount < 24 && srcIndex < size) {
        bits |= data[srcIndex] << bitcount;
        srcIndex += 1;
        bitcount += 8;
      }

      if (!foundNode) {
        node = this.decodedLut[bits & LUTMASK];
      }

      if (node.numBits != 0) {
        bits >>= node.numBits;
        bitcount -= node.numBits;
      } else {
        bits >>= LUTBITS;
        bitcount -= LUTBITS;

        while (true) {
          node = this.nodes[node.leafs[bits & 1]];
          bitcount -= 1;
          bits >>= 1;
          if (node.numBits != 0) {
            break;
          }
          if (bitcount == 0) {
            try {
              throw new HuffmanException("no more bits");
            } catch (HuffmanException ex) {
              System.out.println("huffman decompress failed: " + ex.getMessage());
              ex.printStackTrace();
            }
          }
        }
      }
      if (node == eof) {
        break;
      }
      stream.write(node.symbol);
    }

    return stream.toByteArray();
  }
}
