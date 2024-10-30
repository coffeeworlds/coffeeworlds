package com.github.coffeeworlds.network;

import java.util.Arrays;

public class Packer {
  public byte[] buffer;
  int offset;

  private static final int PACKER_BUFFER_SIZE = 1024 * 2;

  public Packer() {
    this.buffer = new byte[PACKER_BUFFER_SIZE];
    this.offset = 0;
  }

  public byte[] data() {
    return Arrays.copyOfRange(this.buffer, 0, this.offset);
  }

  public int size() {
    return this.offset;
  }

  public void addString(String text) {
    byte[] packedStr = text.getBytes();
    System.arraycopy(packedStr, 0, this.buffer, this.offset, packedStr.length);
    this.offset += packedStr.length;
    this.buffer[this.offset] = 0x00;
    this.offset++;
  }
}
