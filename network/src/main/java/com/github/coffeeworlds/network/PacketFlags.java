package com.github.coffeeworlds.network;

public class PacketFlags {
  public static final int FLAG_CONTROL = 1;
  public static final int FLAG_RESEND = 2;
  public static final int FLAG_COMPRESSION = 4;
  public static final int FLAG_CONNLESS = 8;

  public boolean control = false;
  public boolean resend = false;
  public boolean compression = false;
  public boolean connless = false;

  @Override
  public String toString() {
    String flags = "";
    if (this.control) {
      flags += "control";
    }
    if (this.resend) {
      if (flags != "") {
        flags += ", ";
      }
      flags += "resend";
    }
    if (this.compression) {
      if (flags != "") {
        flags += ", ";
      }
      flags += "compression";
    }
    if (this.connless) {
      if (flags != "") {
        flags += ", ";
      }
      flags += "connless";
    }

    if (flags == "") {
      flags = "null";
    }

    return flags;
  }

  public void fromNumber(int flags) {
    this.control = (flags & FLAG_CONTROL) != 0;
    this.resend = (flags & FLAG_RESEND) != 0;
    this.compression = (flags & FLAG_COMPRESSION) != 0;
    this.connless = (flags & FLAG_CONNLESS) != 0;
  }

  public int toNumber() {
    int flags = 0;
    if (this.control) {
      flags |= FLAG_CONTROL;
    }
    if (this.resend) {
      flags |= FLAG_RESEND;
    }
    if (this.compression) {
      flags |= FLAG_COMPRESSION;
    }
    if (this.connless) {
      flags |= FLAG_CONNLESS;
    }
    return flags;
  }
}
