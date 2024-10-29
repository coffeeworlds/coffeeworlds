package com.github.coffeeworlds.network;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnpackerTest {
    @Test void basicIntZero() {
        byte[] data = new byte[]{0x00};
        Unpacker unpacker = new Unpacker(data);
        assertEquals(unpacker.getInt(), 0);
    }

    @Test void basicIntOne() {
        byte[] data = new byte[]{0x01};
        Unpacker unpacker = new Unpacker(data);
        assertEquals(unpacker.getInt(), 1);
    }

    // @Test void multiByte() {
    //     Unpacker unpacker = new Unpacker(new byte[]{0x3F});
    //     assertEquals(unpacker.getInt(), 63);

    //     byte[] data = new byte[]{0x80, 0x01};
    //     unpacker = new Unpacker(data);
    //     assertEquals(unpacker.getInt(), 64);


    //     // byte[] data = new byte[]{0x80, 0x01};
    //     // Unpacker unpacker = new Unpacker(data);
    //     // assertEquals(unpacker.getInt(), 64);
    // }
}
