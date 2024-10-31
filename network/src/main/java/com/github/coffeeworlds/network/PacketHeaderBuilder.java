package com.github.coffeeworlds.network;

public class PacketHeaderBuilder {
  private int _ack = 0;
  private int _numChunks = 0;
  private byte[] _token;
  private PacketFlags _flags;

  public PacketHeaderBuilder() {
    this._token = new byte[] {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
    this._flags = new PacketFlags();
  }

  PacketHeaderBuilder ack(int acknowledgeNumber) {
    this._ack = acknowledgeNumber;
    return this;
  }

  PacketHeaderBuilder numChunks(int num) {
    this._numChunks = num;
    return this;
  }

  PacketHeaderBuilder token(byte[] securityToken) {
    this._token = securityToken;
    return this;
  }

  PacketHeaderBuilder setFlagControl() {
    this._flags.control = true;
    return this;
  }

  PacketHeaderBuilder setFlagResend() {
    this._flags.resend = true;
    return this;
  }

  PacketHeaderBuilder setFlagCompression() {
    this._flags.compression = true;
    return this;
  }

  PacketHeaderBuilder setFlagConnless() {
    this._flags.connless = true;
    return this;
  }

  PacketHeader buildHeader() {
    return new PacketHeader(this._ack, this._numChunks, this._token, this._flags);
  }
}
