package com.github.coffeeworlds.network;

public class ChunkHeader {
  public ChunkFlags flags;
  public int size;
  public int seq;

  public ChunkHeader(int size, int sequenceNumber, ChunkFlags flags) {
    this.size = size;
    this.seq = sequenceNumber;
    this.flags = flags;
  }

  public ChunkHeader(int sequenceNumber, ChunkFlags flags) {
    this.size = -1;
    this.seq = sequenceNumber;
    this.flags = flags;
  }

  public ChunkHeader(int sequenceNumber) {
    this.size = -1;
    this.seq = 0;
    this.flags = new ChunkFlags();
    this.flags.vital = true;
  }

  public ChunkHeader(byte[] data) {
    this.flags = new ChunkFlags();
    unpack(data);
  }

  public ChunkHeader(Unpacker unpacker) {
    this.flags = new ChunkFlags();
    unpack(unpacker);
  }

  public void unpack(Unpacker unpacker) {
    byte[] data = unpacker.getRaw(2);
    this.flags.fromNumber((data[0] >> 6) & 0x03);
    this.size = ((data[0] & 0x3f) << 6) | (data[1] & 0x3f);

    this.seq = -1;
    if (this.flags.vital) {
      byte thirdByte = unpacker.popByte();
      this.seq = ((data[1] & 0xc0) << 2) | thirdByte;
    }
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    byte[] data = new byte[this.flags.vital ? 3 : 2];
    int flags = this.flags.toNumber();
    data[0] = (byte) (((flags & 0x03) << 6) | ((this.size >> 6) & 0x3f));
    data[1] = (byte) (this.size & 0x3f);
    if (this.flags.vital) {
      data[1] |= (this.seq >> 2) & 0xc0;
      data[2] = (byte) (this.size & 0x3f);
    }
    return data;
  }
}
