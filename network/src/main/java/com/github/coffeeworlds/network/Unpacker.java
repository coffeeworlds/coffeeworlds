package com.github.coffeeworlds.network;

import java.util.Arrays;

public class Unpacker {
  private byte[] fullData;
  private int index = 0;

  public Unpacker(byte[] data) {
    this.fullData = data;
  }

  // get the last byte without consuming it
  public byte getByte() {
    return this.fullData[this.index];
  }

  // consume one byte
  public byte popByte() {
    byte b = this.fullData[this.index];
    this.index++;
    return b;
  }

  // pop the latest int from the data
  public int getInt() {
    int sign = (getByte() >> 6) & 1;
    int res = getByte() & 0x3f;

    // fake loop goto hack
    do {
      if ((getByte() & 0x80) == 0) {
        break;
      }
      this.index++;
      res |= (getByte() & 0x7f) << 6;

      if ((getByte() & 0x80) == 0) {
        break;
      }
      this.index++;
      res |= (getByte() & 0x7f) << (6 + 7);

      if ((getByte() & 0x80) == 0) {
        break;
      }
      this.index++;
      res |= (getByte() & 0x7f) << (6 + 7 + 7);

      if ((getByte() & 0x80) == 0) {
        break;
      }
      this.index++;
      res |= (getByte() & 0x7f) << (6 + 7 + 7 + 7);
    } while (false);

    this.index++;
    res ^= -sign;
    return res;
  }

  // consume one string
  public String getString() {
    int start = this.index;
    int end = this.index;

    // TODO: sanitize support
    while (end < this.fullData.length && this.fullData[end] != 0x00) {
      end++;
    }

    // drop null byte
    this.index = end + 1;
    return new String(Arrays.copyOfRange(this.fullData, start, end));
  }

  // consume size amount of bytes
  public byte[] getRaw(int size) {
    int start = this.index;
    int end = start + size;
    if (end > this.fullData.length) {
      // TODO: throw error here?
      end = this.fullData.length;
    }
    this.index = end;
    return Arrays.copyOfRange(this.fullData, start, end);
  }

  // non consuming dump of the remaining data
  public byte[] getRemainingData() {
    return Arrays.copyOfRange(this.fullData, this.index, this.fullData.length);
  }

  // non consuming data dump
  public byte[] getFullData() {
    return this.fullData;
  }

  public int remainingSize() {
    return this.fullData.length - this.index;
  }
}
