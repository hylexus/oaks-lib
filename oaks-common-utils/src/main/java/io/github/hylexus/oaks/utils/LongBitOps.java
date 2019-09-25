package io.github.hylexus.oaks.utils;

import lombok.NonNull;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class LongBitOps {
    private static final long MASK = 0xFF;
    private static final int BYTE_COUNT_OF_LONG = 8;

    public static byte longToByte(final long i) {
        return (byte) (i & MASK);
    }

    public static byte[] longTo1Byte(final int i) {
        return new byte[]{longToByte(i)};
    }

    public static byte[] longTo8Bytes(final long value) {
        byte[] bytes = new byte[BYTE_COUNT_OF_LONG];
        for (int i = 0; i < BYTE_COUNT_OF_LONG; i++) {
            // (8 - 1 - i) * 8
            // (8 - 1 - i) << 3
            bytes[i] = byteAt((BYTE_COUNT_OF_LONG - 1 - i) << 3, value);
        }
        return bytes;
    }

    public static long longFrom8Bytes(@NonNull final byte[] bytes) {
        return longFrom8Bytes(bytes, 0);
    }

    public static long longFrom8Bytes(@NonNull final byte[] bytes, final int startIndex) {
        //        return ((bytes[0] & MASK) << 56)
        //                |
        //                ((bytes[1] & MASK) << 48)
        //                |
        //                ((bytes[2] & MASK) << 40)
        //                |
        //                ((bytes[3] & MASK) << 32)
        //                |
        //                ((bytes[4] & MASK) << 24)
        //                |
        //                ((bytes[5] & MASK) << 16)
        //                |
        //                ((bytes[6] & MASK) << 8)
        //                |
        //                ((bytes[7] & MASK));
        long value = 0;
        for (int i = 0; i < BYTE_COUNT_OF_LONG; i++) {
            value |= (bytes[startIndex + i] & MASK) << ((BYTE_COUNT_OF_LONG - 1 - i) << 3);
        }
        return value;

    }

    public static byte byteAt(final int index, final long value) {
        return (byte) ((value >>> index) & MASK);
    }
}
