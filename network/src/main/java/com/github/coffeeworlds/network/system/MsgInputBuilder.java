package com.github.coffeeworlds.network.system;

public class MsgInputBuilder {

  private int _ackGameTick;
  private int _predictionTick;
  private int _size;
  private int _direction;
  private int _targetX;
  private int _targetY;
  private boolean _jump;
  private int _fire;
  private boolean _hook;
  private int _playerFlags;
  private int _wantedWeapon;
  private int _nextWeapon;
  private int _prevWeapon;
  private int _pingCorrection;

  public MsgInputBuilder() {
    this._ackGameTick = 0;
    this._predictionTick = 0;
    this._size = 0;
    this._direction = 0;
    this._targetX = 0;
    this._targetY = 0;
    this._jump = false;
    this._fire = 0;
    this._hook = false;
    this._playerFlags = 0;
    this._wantedWeapon = 0;
    this._nextWeapon = 0;
    this._prevWeapon = 0;
    this._pingCorrection = 0;
  }

  public MsgInputBuilder pingCorrection(int pingCorrection) {
    this._pingCorrection = pingCorrection;
    return this;
  }

  public MsgInputBuilder prevWeapon(int prevWeapon) {
    this._prevWeapon = prevWeapon;
    return this;
  }

  public MsgInputBuilder nextWeapon(int nextWeapon) {
    this._nextWeapon = nextWeapon;
    return this;
  }

  public MsgInputBuilder wantedWeapon(int wantedWeapon) {
    this._wantedWeapon = wantedWeapon;
    return this;
  }

  public MsgInputBuilder playerFlags(int playerFlags) {
    this._playerFlags = playerFlags;
    return this;
  }

  public MsgInputBuilder hook(boolean hook) {
    this._hook = hook;
    return this;
  }

  public MsgInputBuilder fire(int fire) {
    this._fire = fire;
    return this;
  }

  public MsgInputBuilder jump(boolean jump) {
    this._jump = jump;
    return this;
  }

  public MsgInputBuilder targetY(int targetY) {
    this._targetY = targetY;
    return this;
  }

  public MsgInputBuilder targetX(int targetX) {
    this._targetX = targetX;
    return this;
  }

  public MsgInputBuilder direction(int direction) {
    this._direction = direction;
    return this;
  }

  public MsgInputBuilder size(int size) {
    this._size = size;
    return this;
  }

  public MsgInputBuilder predictionTick(int predictionTick) {
    this._predictionTick = predictionTick;
    return this;
  }

  public MsgInputBuilder ackGameTick(int ackGameTick) {
    this._ackGameTick = ackGameTick;
    return this;
  }

  public MsgInput buildMsg() {
    return new MsgInput(
        this._ackGameTick,
        this._predictionTick,
        this._size,
        this._direction,
        this._targetX,
        this._targetY,
        this._jump,
        this._fire,
        this._hook,
        this._playerFlags,
        this._wantedWeapon,
        this._nextWeapon,
        this._prevWeapon,
        this._pingCorrection);
  }
}
