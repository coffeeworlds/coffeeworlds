package com.github.coffeeworlds.network;

public class MsgPacker extends Packer {
  public enum MsgType {
    GAME,
    SYSTEM
  }

  public MsgPacker(int MsgId, MsgType Type) {
    addInt((MsgId << 1) | ((Type == MsgType.SYSTEM) ? 1 : 0));
  }
}
