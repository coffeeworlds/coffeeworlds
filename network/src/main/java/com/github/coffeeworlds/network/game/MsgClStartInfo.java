package com.github.coffeeworlds.network.game;

import com.github.coffeeworlds.network.GameMessage;
import com.github.coffeeworlds.network.MsgPacker;
import com.github.coffeeworlds.network.MsgPacker.MsgType;
import com.github.coffeeworlds.network.NetMessage;
import com.github.coffeeworlds.network.Unpacker;

public class MsgClStartInfo implements NetMessage {
  public String name;
  public String clan;
  public int country;
  public String body;
  public String marking;
  public String decoration;
  public String hands;
  public String feet;
  public String eyes;
  public boolean customColorBody;
  public boolean customColorMarking;
  public boolean customColorDecoration;
  public boolean customColorHands;
  public boolean customColorFeet;
  public boolean customColorEyes;
  public int colorBody;
  public int colorMarking;
  public int colorDecoration;
  public int colorHands;
  public int colorFeet;
  public int colorEyes;

  public MsgClStartInfo(
      String name,
      String clan,
      int country,
      String body,
      String marking,
      String decoration,
      String hands,
      String feet,
      String eyes,
      boolean customColorBody,
      boolean customColorMarking,
      boolean customColorDecoration,
      boolean customColorHands,
      boolean customColorFeet,
      boolean customColorEyes,
      int colorBody,
      int colorMarking,
      int colorDecoration,
      int colorHands,
      int colorFeet,
      int colorEyes) {
    this.name = name;
    this.clan = clan;
    this.country = country;
    this.body = body;
    this.marking = marking;
    this.decoration = decoration;
    this.hands = hands;
    this.feet = feet;
    this.eyes = eyes;
    this.customColorBody = customColorBody;
    this.customColorMarking = customColorMarking;
    this.customColorDecoration = customColorDecoration;
    this.customColorHands = customColorHands;
    this.customColorFeet = customColorFeet;
    this.customColorEyes = customColorEyes;
    this.colorBody = colorBody;
    this.colorMarking = colorMarking;
    this.colorDecoration = colorDecoration;
    this.colorHands = colorHands;
    this.colorFeet = colorFeet;
    this.colorEyes = colorEyes;
  }

  public MsgClStartInfo(String name) {
    resetToSensibleDefaults();
    this.name = name;
  }

  public MsgClStartInfo() {
    resetToSensibleDefaults();
  }

  public MsgClStartInfo(byte[] data) {
    unpack(data);
  }

  public void resetToSensibleDefaults() {
    this.name = "coffeeworlds";
    this.clan = "";
    this.country = -1;
    this.body = "greensward";
    this.marking = "duodonny";
    this.decoration = "";
    this.hands = "standard";
    this.feet = "standard";
    this.eyes = "standard";
    this.customColorBody = true;
    this.customColorMarking = true;
    this.customColorDecoration = false;
    this.customColorHands = false;
    this.customColorFeet = false;
    this.customColorEyes = false;
    this.colorBody = 5635840;
    this.colorMarking = -11141356;
    this.colorDecoration = 65408;
    this.colorHands = 65408;
    this.colorFeet = 65408;
    this.colorEyes = 65408;
  }

  public MsgClStartInfo(Unpacker unpacker) {
    unpack(unpacker);
  }

  @Override
  public String toString() {
    return String.format("<MsgClStartInfo name='%s' clan='%s'>", this.name, this.clan);
  }

  public String name() {
    return this.getClass().getSimpleName();
  }

  public void unpack(Unpacker unpacker) {
    this.name = unpacker.getString();
    this.clan = unpacker.getString();
    this.country = unpacker.getInt();
    this.body = unpacker.getString();
    this.marking = unpacker.getString();
    this.decoration = unpacker.getString();
    this.hands = unpacker.getString();
    this.feet = unpacker.getString();
    this.eyes = unpacker.getString();
    this.customColorBody = unpacker.getBoolean();
    this.customColorMarking = unpacker.getBoolean();
    this.customColorDecoration = unpacker.getBoolean();
    this.customColorHands = unpacker.getBoolean();
    this.customColorFeet = unpacker.getBoolean();
    this.customColorEyes = unpacker.getBoolean();
    this.colorBody = unpacker.getInt();
    this.colorMarking = unpacker.getInt();
    this.colorDecoration = unpacker.getInt();
    this.colorHands = unpacker.getInt();
    this.colorFeet = unpacker.getInt();
    this.colorEyes = unpacker.getInt();
  }

  public void unpack(byte[] data) {
    Unpacker unpacker = new Unpacker(data);
    unpack(unpacker);
  }

  public byte[] pack() {
    MsgPacker msg = new MsgPacker(GameMessage.CL_STARTINFO, MsgType.GAME);
    msg.addString(this.name);
    msg.addString(this.clan);
    msg.addInt(this.country);
    msg.addString(this.body);
    msg.addString(this.marking);
    msg.addString(this.decoration);
    msg.addString(this.hands);
    msg.addString(this.feet);
    msg.addString(this.eyes);
    msg.addBoolean(this.customColorBody);
    msg.addBoolean(this.customColorMarking);
    msg.addBoolean(this.customColorDecoration);
    msg.addBoolean(this.customColorHands);
    msg.addBoolean(this.customColorFeet);
    msg.addBoolean(this.customColorEyes);
    msg.addInt(this.colorBody);
    msg.addInt(this.colorMarking);
    msg.addInt(this.colorDecoration);
    msg.addInt(this.colorHands);
    msg.addInt(this.colorFeet);
    msg.addInt(this.colorEyes);
    return msg.data();
  }
}
