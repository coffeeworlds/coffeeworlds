package com.github.coffeeworlds.network.system;

public class MsgInfoBuilder {
  private String _netVersion;
  private String _password;
  private int _clientVersion;

  public MsgInfoBuilder() {
    this._netVersion = "0.7 802f1be60a05665f";
    this._password = "";
    this._clientVersion = 0x0705;
  }

  // default "0.7 802f1be60a05665f"
  public MsgInfoBuilder netVersion(String version) {
    this._netVersion = version;
    return this;
  }

  // default "" empty
  public MsgInfoBuilder password(String pass) {
    this._password = pass;
    return this;
  }

  // default 0x0705
  public MsgInfoBuilder clientVersion(int version) {
    this._clientVersion = version;
    return this;
  }

  public MsgInfo buildMsg() {
    return new MsgInfo(this._netVersion, this._password, this._clientVersion);
  }
}
