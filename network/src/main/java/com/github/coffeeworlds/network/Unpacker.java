package com.github.coffeeworlds.network;

public class Unpacker {
    public byte[] fullData;
    public int index = 0;

    public Unpacker(byte[] data) {
        this.fullData = data;
    }

    // get the last byte without consuming it
    public byte getByte() {
        return this.fullData[index];
    }

    // pop the latest int from the data
    public int getInt() {
        int sign = (getByte() >> 6) & 1;
        int res = getByte() & 0x3f;

        // fake loop goto hack
        do {
            if ((getByte() & 0x80) == 0) {
                break;
            }
            this.index++;
            res |= (getByte() & 0x7f) << 6;

            if ((getByte() & 0x80) == 0) {
                break;
            }
            this.index++;
            res |= (getByte() & 0x7f) << (6 + 7);

            if ((getByte() & 0x80) == 0) {
                break;
            }
            this.index++;
            res |= (getByte() & 0x7f) << (6 + 7 + 7);

            if ((getByte() & 0x80) == 0) {
                break;
            }
            this.index++;
            res |= (getByte() & 0x7f) << (6 + 7 + 7 + 7);
        } while (false);

        this.index++;
        res ^= -sign;
        return res;
    }
}

