package com.github.coffeeworlds.network;

public class SnapshotStorage {
  public static final int UNINITIALIZED_TICK = -1;

  public int newestTick;
  public int oldestTick;

  public SnapshotStorage() {
    this.newestTick = UNINITIALIZED_TICK;
    this.oldestTick = UNINITIALIZED_TICK;
  }
}
