package com.github.coffeeworlds.network.game;

import com.github.coffeeworlds.network.GameMessage;
import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgSvTuneParams implements NetMessage {
  public int groundControlSpeed;
  public int groundControlAccel;
  public int groundFriction;
  public int groundJumpImpulse;
  public int airJumpImpulse;
  public int airControlSpeed;
  public int airControlAccel;
  public int airFriction;
  public int hookLength;
  public int hookFireSpeed;
  public int hookDragAccel;
  public int hookDragSpeed;
  public int gravity;
  public int velrampStart;
  public int velrampRange;
  public int velrampCurvature;
  public int gunCurvature;
  public int gunSpeed;
  public int gunLifetime;
  public int shotgunCurvature;
  public int shotgunSpeed;
  public int shotgunSpeeddiff;
  public int shotgunLifetime;
  public int grenadeCurvature;
  public int grenadeSpeed;
  public int grenadeLifetime;
  public int laserReach;
  public int laserBounceDelay;
  public int laserBounceNum;
  public int laserBounceCost;
  public int playerCollision;
  public int playerHooking;

  public MsgSvTuneParams() {
    this.groundControlSpeed = 0;
    this.groundControlAccel = 0;
    this.groundFriction = 0;
    this.groundJumpImpulse = 0;
    this.airJumpImpulse = 0;
    this.airControlSpeed = 0;
    this.airControlAccel = 0;
    this.airFriction = 0;
    this.hookLength = 0;
    this.hookFireSpeed = 0;
    this.hookDragAccel = 0;
    this.hookDragSpeed = 0;
    this.gravity = 0;
    this.velrampStart = 0;
    this.velrampRange = 0;
    this.velrampCurvature = 0;
    this.gunCurvature = 0;
    this.gunSpeed = 0;
    this.gunLifetime = 0;
    this.shotgunCurvature = 0;
    this.shotgunSpeed = 0;
    this.shotgunSpeeddiff = 0;
    this.shotgunLifetime = 0;
    this.grenadeCurvature = 0;
    this.grenadeSpeed = 0;
    this.grenadeLifetime = 0;
    this.laserReach = 0;
    this.laserBounceDelay = 0;
    this.laserBounceNum = 0;
    this.laserBounceCost = 0;
    this.playerCollision = 0;
    this.playerHooking = 0;
  }

  public MsgSvTuneParams(Unpacker unpacker) {
    unpack(unpacker);
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {
    this.groundControlSpeed = unpacker.getInt();
    this.groundControlAccel = unpacker.getInt();
    this.groundFriction = unpacker.getInt();
    this.groundJumpImpulse = unpacker.getInt();
    this.airJumpImpulse = unpacker.getInt();
    this.airControlSpeed = unpacker.getInt();
    this.airControlAccel = unpacker.getInt();
    this.airFriction = unpacker.getInt();
    this.hookLength = unpacker.getInt();
    this.hookFireSpeed = unpacker.getInt();
    this.hookDragAccel = unpacker.getInt();
    this.hookDragSpeed = unpacker.getInt();
    this.gravity = unpacker.getInt();
    this.velrampStart = unpacker.getInt();
    this.velrampRange = unpacker.getInt();
    this.velrampCurvature = unpacker.getInt();
    this.gunCurvature = unpacker.getInt();
    this.gunSpeed = unpacker.getInt();
    this.gunLifetime = unpacker.getInt();
    this.shotgunCurvature = unpacker.getInt();
    this.shotgunSpeed = unpacker.getInt();
    this.shotgunSpeeddiff = unpacker.getInt();
    this.shotgunLifetime = unpacker.getInt();
    this.grenadeCurvature = unpacker.getInt();
    this.grenadeSpeed = unpacker.getInt();
    this.grenadeLifetime = unpacker.getInt();
    this.laserReach = unpacker.getInt();
    this.laserBounceDelay = unpacker.getInt();
    this.laserBounceNum = unpacker.getInt();
    this.laserBounceCost = unpacker.getInt();
    this.playerCollision = unpacker.getInt();
    this.playerHooking = unpacker.getInt();
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(GameMessage.SV_TUNEPARAMS, MsgType.GAME);
    msg.addInt(this.groundControlSpeed);
    msg.addInt(this.groundControlAccel);
    msg.addInt(this.groundFriction);
    msg.addInt(this.groundJumpImpulse);
    msg.addInt(this.airJumpImpulse);
    msg.addInt(this.airControlSpeed);
    msg.addInt(this.airControlAccel);
    msg.addInt(this.airFriction);
    msg.addInt(this.hookLength);
    msg.addInt(this.hookFireSpeed);
    msg.addInt(this.hookDragAccel);
    msg.addInt(this.hookDragSpeed);
    msg.addInt(this.gravity);
    msg.addInt(this.velrampStart);
    msg.addInt(this.velrampRange);
    msg.addInt(this.velrampCurvature);
    msg.addInt(this.gunCurvature);
    msg.addInt(this.gunSpeed);
    msg.addInt(this.gunLifetime);
    msg.addInt(this.shotgunCurvature);
    msg.addInt(this.shotgunSpeed);
    msg.addInt(this.shotgunSpeeddiff);
    msg.addInt(this.shotgunLifetime);
    msg.addInt(this.grenadeCurvature);
    msg.addInt(this.grenadeSpeed);
    msg.addInt(this.grenadeLifetime);
    msg.addInt(this.laserReach);
    msg.addInt(this.laserBounceDelay);
    msg.addInt(this.laserBounceNum);
    msg.addInt(this.laserBounceCost);
    msg.addInt(this.playerCollision);
    msg.addInt(this.playerHooking);
    return msg.data();
  }
}
