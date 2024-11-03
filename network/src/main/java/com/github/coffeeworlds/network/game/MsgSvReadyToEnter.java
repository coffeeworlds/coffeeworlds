package com.github.coffeeworlds.network.game;

import com.github.coffeeworlds.network.GameMessage;
import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgSvReadyToEnter implements NetMessage {
  public MsgSvReadyToEnter(Unpacker unpacker) {}

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {}

  public void unpack(byte[] data) {}

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(GameMessage.SV_READYTOENTER, MsgType.GAME);
    return msg.data();
  }
}
