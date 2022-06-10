package io.github.c2hy.smartstate;

import java.util.Collections;
import java.util.Objects;

class ArraySmartStates {
    static byte[] to32ByteArray(int number) {
        byte[] bytes = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 32; i++) {
            if (number <= 0) {
                break;
            }
            bytes[i] = (byte) (number % 2);
            number = number / 2;
        }
        return bytes;
    }

    static String toBinaryString(byte[] bytes) {
        byte[] newBytes = bytes.clone();
        String[] chars = new String[32];
        for (int i = 0; i < 16; i++) {
            int counterpoint = newBytes.length - 1 - i;
            chars[i] = Objects.toString(newBytes[counterpoint]);
            chars[counterpoint] = Objects.toString(newBytes[i]);
        }
        return String.join("", chars);
    }

    static int toNumber(byte[] bytes) {
        return Integer.parseInt(toBinaryString(bytes), 2);
    }
}
