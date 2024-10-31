package com.github.coffeeworlds.network.game;

import com.github.coffeeworlds.network.GameMessage;
import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;

class MsgSvMotd {
  public String message;

  public MsgSvMotd() {
    this.message = "";
  }

  public MsgSvMotd(String message) {
    this.message = message;
  }

  public byte[] data() {
    MsgPacker msg = new MsgPacker(GameMessage.SV_MOTD, MsgType.GAME);
    msg.addString(this.message);
    return msg.data();
  }
}
