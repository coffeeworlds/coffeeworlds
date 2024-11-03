package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgSnapSingle implements NetMessage {
  public int gameTick;
  public int deltaTick;
  public int crc;
  public int partSize;
  public byte[] data;

  public MsgSnapSingle() {
    this.gameTick = 0;
    this.deltaTick = 0;
    this.crc = 0;
    this.partSize = 0;
    this.data = new byte[0];
  }

  public MsgSnapSingle(byte[] data) {
    unpack(data);
  }

  public MsgSnapSingle(Unpacker unpacker) {
    unpack(unpacker);
  }

  @Override
  public String toString() {
    return String.format("<MsgSnapSingle gameTick=%d>", this.gameTick);
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {
    this.gameTick = unpacker.getInt();
    this.deltaTick = unpacker.getInt();
    this.crc = unpacker.getInt();
    this.partSize = unpacker.getInt();
    this.data = unpacker.getRaw(this.partSize);
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.SNAPSINGLE, MsgType.SYSTEM);
    msg.addInt(this.gameTick);
    msg.addInt(this.deltaTick);
    msg.addInt(this.crc);
    msg.addInt(this.partSize);
    msg.addRaw(this.data);
    return msg.data();
  }
}
