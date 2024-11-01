package com.github.coffeeworlds.network;

public interface NetMessage {
  public ChunkHeader header = null;

  // message name
  public String name();

  // unpack message payload
  // data has to point to the first field
  // in the message
  // NOT to the chunk header or msg id
  public void unpack(byte[] data);

  // packs message with msg id and system flag in front
  // NO chunk header
  public byte[] pack();
}
