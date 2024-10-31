package com.github.coffeeworlds.network.game;

import com.github.coffeeworlds.network.GameMessage;
import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgSvMotd implements NetMessage {
  public String message;

  public MsgSvMotd() {
    this.message = "";
  }

  public MsgSvMotd(String message) {
    this.message = message;
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    this.message = unpacker.getString();
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(GameMessage.SV_MOTD, MsgType.GAME);
    msg.addString(this.message);
    return msg.data();
  }
}
