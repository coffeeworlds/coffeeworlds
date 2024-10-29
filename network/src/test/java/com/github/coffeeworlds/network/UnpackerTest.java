package com.github.coffeeworlds.network;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnpackerTest {
    @Test void basicIntZero() {
        byte[] data = new byte[]{0x00};
        Unpacker unpacker = new Unpacker(data);
        assertEquals(0, unpacker.getInt());
    }

    @Test void basicIntOne() {
        byte[] data = new byte[]{0x01};
        Unpacker unpacker = new Unpacker(data);
        assertEquals(1, unpacker.getInt());
    }

    // @Test void multiByte() {
    //     Unpacker unpacker = new Unpacker(new byte[]{0x3F});
    //     assertEquals(63, unpacker.getInt());

    //     unpacker = new Unpacker(new byte[]{(byte)0x80, 0x01, 0x02});
    //     assertEquals(64, unpacker.getInt());

    //     unpacker = new Unpacker(new byte[]{(byte)0x80, 0x01});
    //     assertEquals(64, unpacker.getInt());
    // }
}
