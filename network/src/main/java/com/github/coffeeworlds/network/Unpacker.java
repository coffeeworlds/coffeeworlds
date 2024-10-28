package com.github.coffeeworlds.network;

public class Unpacker {
    public byte[] data;

    public Unpacker(byte[] rawData) {
        data = rawData;
    }

    public int getInt() {
        return data[0];
    }
}

