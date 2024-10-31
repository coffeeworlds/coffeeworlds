package com.github.coffeeworlds.network;

import com.github.coffeeworlds.network.MsgPacker.MsgType;

public class MessageMatcher {
  // The amount of vital chunks received
  public int ack;

  public MessageMatcher(int ack) {
    this.ack = ack;
  }

  public boolean matchSys(int msgId) {
    if (msgId == SystemMessage.INFO) {
      return true;
    }
    return false;
  }

  // return true if a message was consumed
  // returns falls if end of data is hit
  public boolean getMessage(Unpacker unpacker) {
    ChunkHeader header = new ChunkHeader(unpacker);
    int msgAndSys = unpacker.getInt();
    MsgType msgType = ((msgAndSys & 1) == 0) ? MsgType.GAME : MsgType.SYSTEM;
    int msgId = msgAndSys >> 1;

    if (msgType == MsgType.SYSTEM) {
      return matchSys(msgId);
    }

    return false;
  }
}
