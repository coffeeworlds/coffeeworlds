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

    @Test void smallNegativeInt() {
        byte[] data = new byte[]{0x40};
        Unpacker unpacker = new Unpacker(data);
        assertEquals(-1, unpacker.getInt());
    }

    @Test void multiByteNegativeInt() {
        Unpacker unpacker = new Unpacker(new byte[]{0x7e});
        assertEquals(-63, unpacker.getInt());

        unpacker = new Unpacker(new byte[]{0x7f});
        assertEquals(-64, unpacker.getInt());

        unpacker = new Unpacker(new byte[]{(byte)0xc0, 0x1});
        assertEquals(-65, unpacker.getInt());
    }

    @Test void multiByteInts() {
        Unpacker unpacker = new Unpacker(new byte[]{0x3f});
        assertEquals(63, unpacker.getInt());

        unpacker = new Unpacker(new byte[]{(byte)0x80, 0x01});
        assertEquals(64, unpacker.getInt());

        unpacker = new Unpacker(new byte[]{(byte)0x81, 0x01});
        assertEquals(65, unpacker.getInt());

        unpacker = new Unpacker(new byte[]{(byte)0x82, 0x01});
        assertEquals(66, unpacker.getInt());
    }

    @Test void multipleInts() {
        byte[] data = new byte[]{0x40, 0x01, (byte)0x80, 0x01, 0x02};
        Unpacker unpacker = new Unpacker(data);
        assertEquals(-1, unpacker.getInt());
        assertEquals(1, unpacker.getInt());
        assertEquals(64, unpacker.getInt());
        assertEquals(2, unpacker.getInt());
    }
}
