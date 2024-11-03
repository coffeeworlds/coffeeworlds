package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgEnterGame implements NetMessage {
  public MsgEnterGame() {}

  public MsgEnterGame(byte[] data) {}

  public MsgEnterGame(Unpacker unpacker) {}

  @Override
  public String toString() {
    return "<MsgEnterGame>";
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {}

  public void unpack(byte[] data) {}

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.ENTERGAME, MsgType.SYSTEM);
    return msg.data();
  }
}
