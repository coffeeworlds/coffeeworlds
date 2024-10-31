package com.github.coffeeworlds.network;

public class Chunk {
  public ChunkHeader header;
  public NetMessage message;

  public Chunk(ChunkHeader header, NetMessage msg) {
    this.header = header;
    this.message = msg;
  }
}
