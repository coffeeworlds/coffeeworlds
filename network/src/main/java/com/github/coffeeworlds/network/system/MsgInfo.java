package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.SystemMessage;

class MsgInfo {
  private String _netVersion;
  private String _password;
  private int _clientVersion;

  public MsgInfo() {
    this._netVersion = "0.7 802f1be60a05665f";
    this._password = "";
    this._clientVersion = 0x0705;
  }

  // default "0.7 802f1be60a05665f"
  public MsgInfo netVersion(String version) {
    this._netVersion = version;
    return this;
  }

  // default "" empty
  public MsgInfo password(String pass) {
    this._password = pass;
    return this;
  }

  // default 0x0705
  public MsgInfo clientVersion(int version) {
    this._clientVersion = version;
    return this;
  }

  public byte[] data() {
    MsgPacker msg = new MsgPacker(SystemMessage.INFO, MsgType.SYSTEM);
    msg.addString(this._netVersion);
    msg.addString(this._password);
    msg.addInt(this._clientVersion);
    return msg.data();
  }
}
