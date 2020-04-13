package com.example.demo.utils;

import java.util.UUID;

public class UUIDUtils {
    static final char[] DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                    'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
                    'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static String to62RadixString(long seq) {
        StringBuilder sBuilder = new StringBuilder();
        while (true) {
            int remainder = (int) (seq % 62);
            sBuilder.append(DIGITS[remainder]);
            seq = seq / 62;
            if (seq == 0) {
                break;
            }
        }
        return sBuilder.reverse().toString();
    }

    /**
     * 采用62进制UUID
     *
     * @return
     */
    public static String get62RadixId() {
        Long uuid = UUID.randomUUID().getLeastSignificantBits();
        uuid = Math.abs(uuid);
        return to62RadixString(uuid);
    }
}
