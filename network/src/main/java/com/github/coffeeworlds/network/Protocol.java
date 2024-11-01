package com.github.coffeeworlds.network;

public class Protocol {
  public static final int REFLECTION_PROTECTION_PAYLOAD_SIZE = 512;
  public static final int NET_MAX_CHUNKHEADERSIZE = 3;
  public static final int NET_PACKETHEADERSIZE = 7;
  public static final int NET_PACKETHEADERSIZE_CONNLESS = NET_PACKETHEADERSIZE + 2;
  public static final int NET_MAX_PACKETHEADERSIZE = NET_PACKETHEADERSIZE_CONNLESS;
  public static final int NET_MAX_PACKETSIZE = 1400;
  public static final int NET_MAX_PAYLOAD = NET_MAX_PACKETSIZE - NET_MAX_PACKETHEADERSIZE;
  public static final int NET_MAX_CLIENTS = 64;
}
