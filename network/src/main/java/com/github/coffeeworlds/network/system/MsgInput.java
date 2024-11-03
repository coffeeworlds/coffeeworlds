package com.github.coffeeworlds.network.system;

import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.SystemMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgInput implements NetMessage {
  public int ackGameTick;
  public int predictionTick;
  public int size;
  public int direction;
  public int targetX;
  public int targetY;
  public boolean jump;
  public int fire;
  public boolean hook;
  public int playerFlags;
  public int wantedWeapon;
  public int nextWeapon;
  public int prevWeapon;
  public int pingCorrection;

  public MsgInput(
      int ackGameTick,
      int predictionTick,
      int size,
      int direction,
      int targetX,
      int targetY,
      boolean jump,
      int fire,
      boolean hook,
      int playerFlags,
      int wantedWeapon,
      int nextWeapon,
      int prevWeapon,
      int pingCorrection) {
    this.ackGameTick = ackGameTick;
    this.predictionTick = predictionTick;
    this.size = size;
    this.direction = direction;
    this.targetX = targetX;
    this.targetY = targetY;
    this.jump = jump;
    this.fire = fire;
    this.hook = hook;
    this.playerFlags = playerFlags;
    this.wantedWeapon = wantedWeapon;
    this.nextWeapon = nextWeapon;
    this.prevWeapon = prevWeapon;
    this.pingCorrection = pingCorrection;
  }

  public MsgInput() {
    this.ackGameTick = 0;
    this.predictionTick = 0;
    this.size = 0;
    this.direction = 0;
    this.targetX = 0;
    this.targetY = 0;
    this.jump = false;
    this.fire = 0;
    this.hook = false;
    this.playerFlags = 0;
    this.wantedWeapon = 0;
    this.nextWeapon = 0;
    this.prevWeapon = 0;
    this.pingCorrection = 0;
  }

  public MsgInput(byte[] data) {
    unpack(data);
  }

  public MsgInput(Unpacker unpacker) {
    unpack(unpacker);
  }

  @Override
  public String toString() {
    return String.format("<MsgInput direction=%d>", this.direction);
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {
    this.ackGameTick = unpacker.getInt();
    this.predictionTick = unpacker.getInt();
    this.size = unpacker.getInt();
    this.direction = unpacker.getInt();
    this.targetX = unpacker.getInt();
    this.targetY = unpacker.getInt();
    this.jump = unpacker.getBoolean();
    this.fire = unpacker.getInt();
    this.hook = unpacker.getBoolean();
    this.playerFlags = unpacker.getInt();
    this.wantedWeapon = unpacker.getInt();
    this.nextWeapon = unpacker.getInt();
    this.prevWeapon = unpacker.getInt();
    this.pingCorrection = unpacker.getInt();
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(SystemMessage.INPUT, MsgType.SYSTEM);
    msg.addInt(this.ackGameTick);
    msg.addInt(this.predictionTick);
    msg.addInt(this.size);
    msg.addInt(this.direction);
    msg.addInt(this.targetX);
    msg.addInt(this.targetY);
    msg.addBoolean(this.jump);
    msg.addInt(this.fire);
    msg.addBoolean(this.hook);
    msg.addInt(this.playerFlags);
    msg.addInt(this.wantedWeapon);
    msg.addInt(this.nextWeapon);
    msg.addInt(this.prevWeapon);
    msg.addInt(this.pingCorrection);
    return msg.data();
  }
}
