package com.github.coffeeworlds.network.game;

import com.github.coffeeworlds.network.GameMessage;
import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;

class MsgSvMotd {
  private String _message;

  public MsgSvMotd() {
    this._message = "";
  }

  public MsgSvMotd message(String text) {
    this._message = text;
    return this;
  }

  public byte[] data() {
    MsgPacker msg = new MsgPacker(GameMessage.SV_MOTD, MsgType.GAME);
    msg.addString(this._message);
    return msg.data();
  }
}
