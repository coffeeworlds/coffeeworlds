package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgReady implements NetMessage {
  public MsgReady() {}

  public MsgReady(byte[] data) {}

  public MsgReady(Unpacker unpacker) {}

  @Override
  public String toString() {
    return "<MsgReady>";
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {}

  public void unpack(byte[] data) {}

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.READY, MsgType.SYSTEM);
    return msg.data();
  }
}
