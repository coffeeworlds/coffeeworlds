package com.github.coffeeworlds.network.game;

public class MsgClStartInfoBuilder {
  public String _name;
  public String _clan;
  public int _country;
  public String _body;
  public String _marking;
  public String _decoration;
  public String _hands;
  public String _feet;
  public String _eyes;
  public boolean _customColorBody;
  public boolean _customColorMarking;
  public boolean _customColorDecoration;
  public boolean _customColorHands;
  public boolean _customColorFeet;
  public boolean _customColorEyes;
  public int _colorBody;
  public int _colorMarking;
  public int _colorDecoration;
  public int _colorHands;
  public int _colorFeet;
  public int _colorEyes;

  public MsgClStartInfoBuilder() {
    this._name = "coffeeworlds";
    this._clan = "";
    this._country = -1;
    this._body = "greensward";
    this._marking = "duodonny";
    this._decoration = "";
    this._hands = "standard";
    this._feet = "standard";
    this._eyes = "standard";
    this._customColorBody = true;
    this._customColorMarking = true;
    this._customColorDecoration = false;
    this._customColorHands = false;
    this._customColorFeet = false;
    this._customColorEyes = false;
    this._colorBody = 5635840;
    this._colorMarking = -11141356;
    this._colorDecoration = 65408;
    this._colorHands = 65408;
    this._colorFeet = 65408;
    this._colorEyes = 65408;
  }

  public MsgClStartInfoBuilder name(String name) {
    this._name = name;
    return this;
  }

  public MsgClStartInfoBuilder clan(String clan) {
    this._clan = clan;
    return this;
  }

  public MsgClStartInfoBuilder country(int country) {
    this._country = country;
    return this;
  }

  public MsgClStartInfoBuilder body(String body) {
    this._body = body;
    return this;
  }

  public MsgClStartInfoBuilder marking(String marking) {
    this._marking = marking;
    return this;
  }

  public MsgClStartInfoBuilder decoration(String decoration) {
    this._decoration = decoration;
    return this;
  }

  public MsgClStartInfoBuilder hands(String hands) {
    this._hands = hands;
    return this;
  }

  public MsgClStartInfoBuilder feet(String feet) {
    this._feet = feet;
    return this;
  }

  public MsgClStartInfoBuilder eyes(String eyes) {
    this._eyes = eyes;
    return this;
  }

  public MsgClStartInfoBuilder customColorBody(boolean customColorBody) {
    this._customColorBody = customColorBody;
    return this;
  }

  public MsgClStartInfoBuilder customColorMarking(boolean customColorMarking) {
    this._customColorMarking = customColorMarking;
    return this;
  }

  public MsgClStartInfoBuilder customColorDecoration(boolean customColorDecoration) {
    this._customColorDecoration = customColorDecoration;
    return this;
  }

  public MsgClStartInfoBuilder customColorHands(boolean customColorHands) {
    this._customColorHands = customColorHands;
    return this;
  }

  public MsgClStartInfoBuilder customColorFeet(boolean customColorFeet) {
    this._customColorFeet = customColorFeet;
    return this;
  }

  public MsgClStartInfoBuilder customColorEyes(boolean customColorEyes) {
    this._customColorEyes = customColorEyes;
    return this;
  }

  public MsgClStartInfoBuilder colorBody(int colorBody) {
    this._colorBody = colorBody;
    return this;
  }

  public MsgClStartInfoBuilder colorMarking(int colorMarking) {
    this._colorMarking = colorMarking;
    return this;
  }

  public MsgClStartInfoBuilder colorDecoration(int colorDecoration) {
    this._colorDecoration = colorDecoration;
    return this;
  }

  public MsgClStartInfoBuilder colorHands(int colorHands) {
    this._colorHands = colorHands;
    return this;
  }

  public MsgClStartInfoBuilder colorFeet(int colorFeet) {
    this._colorFeet = colorFeet;
    return this;
  }

  public MsgClStartInfoBuilder colorEyes(int colorEyes) {
    this._colorEyes = colorEyes;
    return this;
  }

  public MsgClStartInfo buildMsg() {
    return new MsgClStartInfo(
        this._name,
        this._clan,
        this._country,
        this._body,
        this._marking,
        this._decoration,
        this._hands,
        this._feet,
        this._eyes,
        this._customColorBody,
        this._customColorMarking,
        this._customColorDecoration,
        this._customColorHands,
        this._customColorFeet,
        this._customColorEyes,
        this._colorBody,
        this._colorMarking,
        this._colorDecoration,
        this._colorHands,
        this._colorFeet,
        this._colorEyes);
  }
}
