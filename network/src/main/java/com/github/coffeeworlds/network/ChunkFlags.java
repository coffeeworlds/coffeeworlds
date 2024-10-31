package com.github.coffeeworlds.network;

public class ChunkFlags {
  public static final int CHUNKFLAG_VITAL = 1;
  public static final int CHUNKFLAG_RESEND = 2;

  public boolean vital = false;
  public boolean resend = false;

  public void fromNumber(int flags) {
    this.vital = (flags & CHUNKFLAG_VITAL) != 0;
    this.resend = (flags & CHUNKFLAG_RESEND) != 0;
  }

  public int toNumber() {
    int flags = 0;
    if (this.vital) {
      flags |= CHUNKFLAG_VITAL;
    }
    if (this.resend) {
      flags |= CHUNKFLAG_RESEND;
    }
    return flags;
  }
}
