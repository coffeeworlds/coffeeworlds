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

  public PacketHeaderBuilder ack(int acknowledgeNumber) {
    this._ack = acknowledgeNumber;
    return this;
  }

  public PacketHeaderBuilder numChunks(int num) {
    this._numChunks = num;
    return this;
  }

  public PacketHeaderBuilder token(byte[] securityToken) {
    this._token = securityToken;
    return this;
  }

  public PacketHeaderBuilder setFlagControl() {
    this._flags.control = true;
    return this;
  }

  public PacketHeaderBuilder setFlagControl(boolean control) {
    this._flags.control = control;
    return this;
  }

  public PacketHeaderBuilder setFlagResend() {
    this._flags.resend = true;
    return this;
  }

  public PacketHeaderBuilder setFlagCompression() {
    this._flags.compression = true;
    return this;
  }

  public PacketHeaderBuilder setFlagConnless() {
    this._flags.connless = true;
    return this;
  }

  public PacketHeader buildHeader() {
    return new PacketHeader(this._ack, this._numChunks, this._token, this._flags);
  }
}
