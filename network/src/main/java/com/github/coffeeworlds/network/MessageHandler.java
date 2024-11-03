package com.github.coffeeworlds.network;

import com.github.coffeeworlds.network.game.MsgSvReadyToEnter;
import com.github.coffeeworlds.network.system.MsgConReady;
import com.github.coffeeworlds.network.system.MsgMapChange;

public class MessageHandler {
  public void onMapChange(MsgMapChange msg) {
    System.out.println("got map change: " + msg.mapName);
  }

  public void onConReady(MsgConReady msg) {
    System.out.println("connection is ready");
  }

  public void onReadyToEnter(MsgSvReadyToEnter msg) {}
}
