package com.github.coffeeworlds.network;

public class PacketHeader {
  private byte[] buffer;

  public byte[] token;
  public int ack;
  public int numChunks;
  public PacketFlags packetFlags;

  public PacketHeader(int ack, int numChunks, byte[] token, PacketFlags flags) {
    this.buffer = new byte[7];
    this.ack = ack;
    this.numChunks = numChunks;
    this.token = token;
    this.packetFlags = flags;
  }

  private void packToken() {
    this.buffer[3] = this.token[0];
    this.buffer[4] = this.token[1];
    this.buffer[5] = this.token[2];
    this.buffer[6] = this.token[3];
  }

  private void packtFlagsAndAck() {
    int flags = this.packetFlags.toNumber();
    this.buffer[0] = (byte) ((flags << 2) & 0xfc | ((this.ack >> 8) & 0x03));
    this.buffer[1] = (byte) (this.ack & 0xff);
  }

  private void packNumChunks() {
    this.buffer[2] = (byte) this.numChunks;
  }

  public byte[] pack() {
    packtFlagsAndAck();
    packNumChunks();
    packToken();
    return this.buffer;
  }
}
