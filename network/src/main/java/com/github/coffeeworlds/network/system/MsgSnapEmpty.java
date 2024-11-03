package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgSnapEmpty implements NetMessage {
  public int gameTick;
  public int deltaTick;

  public MsgSnapEmpty() {
    this.gameTick = 0;
    this.deltaTick = 0;
  }

  public MsgSnapEmpty(byte[] data) {
    unpack(data);
  }

  public MsgSnapEmpty(Unpacker unpacker) {
    unpack(unpacker);
  }

  @Override
  public String toString() {
    return String.format("<MsgSnapEmpty gameTick=%d>", this.gameTick);
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {
    this.gameTick = unpacker.getInt();
    this.deltaTick = unpacker.getInt();
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.SNAPEMPTY, MsgType.SYSTEM);
    msg.addInt(this.gameTick);
    msg.addInt(this.deltaTick);
    return msg.data();
  }
}
