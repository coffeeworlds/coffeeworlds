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

  public void addRaw(byte[] data) {
    System.arraycopy(data, 0, this.buffer, this.offset, data.length);
    this.offset += data.length;
  }

  public void addBoolean(boolean value) {
    addInt(value ? 1 : 0);
  }

  public void addInt(int num) {
    if (num < 0) {
      this.buffer[this.offset] |= 0x40; // set sign bit
      num = ~num;
    }

    this.buffer[this.offset] |= num & 0x3f; // pack 6bit into dest
    num >>= 6; // discard 6 bits
    while (num != 0) {
      this.buffer[this.offset] |= 0x80; // set extend bit
      this.offset++;
      this.buffer[this.offset] = (byte) (num & 0x7f); // pack 7bit
      num >>= 7; // discard 7 bits
    }
    this.offset++;
  }

  public void addString(String text) {
    byte[] packedStr = text.getBytes();
    System.arraycopy(packedStr, 0, this.buffer, this.offset, packedStr.length);
    this.offset += packedStr.length;
    this.buffer[this.offset] = 0x00;
    this.offset++;
  }
}
