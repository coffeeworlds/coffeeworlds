package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgInfo implements NetMessage {
  public String netVersion;
  public String password;
  public int clientVersion;

  public MsgInfo(String netVersion, String password, int clientVersion) {
    this.netVersion = netVersion;
    this.password = password;
    this.clientVersion = clientVersion;
  }

  public MsgInfo(String password) {
    this.netVersion = "0.7 802f1be60a05665f";
    this.password = password;
    this.clientVersion = 0x0705;
  }

  public MsgInfo() {
    this.netVersion = "0.7 802f1be60a05665f";
    this.password = "";
    this.clientVersion = 0x0705;
  }

  public MsgInfo(byte[] data) {
    unpack(data);
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    this.netVersion = unpacker.getString();
    this.password = unpacker.getString();
    this.clientVersion = unpacker.getInt();
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.INFO, MsgType.SYSTEM);
    msg.addString(this.netVersion);
    msg.addString(this.password);
    msg.addInt(this.clientVersion);
    return msg.data();
  }
}
