package com.github.coffeeworlds.client;

import com.github.coffeeworlds.network.Unpacker;

public class Client {
    public static void main(String[] args) {
        byte[] data = new byte[]{0x00};
        Unpacker unpacker = new Unpacker(data);
        int val = unpacker.getInt();
        System.out.println("hello from coffee client val=" + val);
    }
}

