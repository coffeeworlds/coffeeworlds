package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgMapChange implements NetMessage {
  public String mapName;
  public int crc;
  public int mapSize;
  public int chunksPerRequest;
  public int chunkSize;
  public byte[] sha256;

  public MsgMapChange(String mapName, int crc, int mapSize, int chunksPerRequest, byte[] sha256) {
    this.mapName = mapName;
    this.crc = crc;
    this.mapSize = mapSize;
    this.chunksPerRequest = chunksPerRequest;
    this.sha256 = sha256;
  }

  public MsgMapChange() {
    this.mapName = "dm1";
    this.crc = 0;
    this.mapSize = 0;
    this.chunksPerRequest = 0;
    this.sha256 = new byte[32];
  }

  public MsgMapChange(byte[] data) {
    unpack(data);
  }

  public MsgMapChange(Unpacker unpacker) {
    unpack(unpacker);
  }

  @Override
  public String toString() {
    return String.format("<MsgMapChange mapName='%s' mapSize=%d>", this.mapName, this.mapSize);
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {
    this.mapName = unpacker.getString();
    this.crc = unpacker.getInt();
    this.mapSize = unpacker.getInt();
    this.chunksPerRequest = unpacker.getInt();
    this.chunkSize = unpacker.getInt();
    this.sha256 = unpacker.getRaw(32);
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.MAP_CHANGE, MsgType.SYSTEM);
    msg.addString(this.mapName);
    msg.addInt(this.crc);
    msg.addInt(this.mapSize);
    msg.addInt(this.chunksPerRequest);
    msg.addInt(this.chunkSize);
    msg.addRaw(this.sha256);
    return msg.data();
  }
}
