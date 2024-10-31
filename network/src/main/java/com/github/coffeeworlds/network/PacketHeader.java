package com.github.coffeeworlds.network;

import java.util.Arrays;

public class PacketHeader {
  private byte[] buffer;

  public byte[] token;
  public int ack;
  public int numChunks;
  public PacketFlags flags;

  public PacketHeader(int ack, int numChunks, byte[] token, PacketFlags flags) {
    this.buffer = new byte[7];
    this.ack = ack;
    this.numChunks = numChunks;
    this.token = token;
    this.flags = flags;
  }

  public PacketHeader(byte[] data) {
    this.buffer = new byte[7];
    this.flags = new PacketFlags();
    unpack(data);
  }

  private void packToken() {
    this.buffer[3] = this.token[0];
    this.buffer[4] = this.token[1];
    this.buffer[5] = this.token[2];
    this.buffer[6] = this.token[3];
  }

  private void packtFlagsAndAck() {
    int flags = this.flags.toNumber();
    this.buffer[0] = (byte) ((flags << 2) & 0xfc | ((this.ack >> 8) & 0x03));
    this.buffer[1] = (byte) (this.ack & 0xff);
  }

  private void packNumChunks() {
    this.buffer[2] = (byte) this.numChunks;
  }

  private void unpackFlags(byte flagByte) {
    int flagBits = (flagByte & 0xfc) >> 2;
    this.flags.fromNumber(flagBits);
  }

  private void unpackAck(byte[] data) {
    this.ack = ((data[0] & 0x03) << 8) | data[1];
  }

  private void unpackNumChunks(byte[] data) {
    this.numChunks = data[2];
  }

  private void unpackToken(byte[] data) {
    this.token = Arrays.copyOfRange(data, 3, 7);
  }

  public void unpack(byte[] data) {
    unpackFlags(data[0]);
    unpackAck(data);
    unpackNumChunks(data);
    unpackToken(data);
  }

  public byte[] pack() {
    packtFlagsAndAck();
    packNumChunks();
    packToken();
    return this.buffer;
  }
}
