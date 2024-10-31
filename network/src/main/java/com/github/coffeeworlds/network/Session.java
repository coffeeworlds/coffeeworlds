package com.github.coffeeworlds.network;

public class Session {
  // our own security token
  public byte[] token;

  // the peers (server) security token
  public byte[] peerToken;

  // sequence number - The amount of vital chunks sent
  public int sequence;

  // acknowledge - The amount of vital chunks received
  public int ack;

  // peer acknowledge - The amount of vital chunks acknowledged by the peer
  public int peerAck;

  public Session() {
    // TODO: this should be randomly generated
    this.token = new byte[] {(byte) 0xc0, (byte) 0xff, (byte) 0xee, 0x00};
    this.peerToken = new byte[] {(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff};
    this.sequence = 0;
    this.ack = 0;
    this.peerAck = 0;
  }
}
