package io.github.hylexus.oaks.utils;

import lombok.NonNull;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class IntBitOps {

    private static final int MASK = 0xFF;

    public static byte intToByte(final int i) {
        return (byte) (i & MASK);
    }

    public static byte[] intTo1Byte(final int i) {
        return new byte[]{intToByte(i)};
    }


    public static int intFrom1Byte(@NonNull final byte[] bytes, final int startIndex) {
        return bytes[startIndex] & MASK;
    }

    public static int intFrom1Byte(final byte b) {
        return b & MASK;
    }

    public static byte[] intTo2Bytes(final int value) {
        return new byte[]{
                (byte) ((value >>> 8) & MASK),
                (byte) (value & MASK)
        };
    }

    public static int intFrom2Bytes(@NonNull final byte[] bytes, final int startIndex) {
        return ((bytes[startIndex] & MASK) << 8)
                |
                (bytes[startIndex + 1] & MASK);
    }

    public static int intFrom2Bytes(@NonNull final byte[] bytes) {
        return intFrom2Bytes(bytes, 0);
    }

    public static byte[] intTo3Bytes(int value) {
        return new byte[]{
                (byte) ((value >>> 16) & MASK),
                (byte) ((value >>> 8) & MASK),
                (byte) (value & MASK)
        };
    }

    public static int intFrom3Bytes(@NonNull final byte[] bytes, final int startIndex) {
        return ((bytes[startIndex] & MASK) << 16)
                |
                ((bytes[startIndex + 1] & MASK) << 8)
                |
                ((bytes[startIndex + 2] & MASK));
    }

    public static int intFrom3Bytes(@NonNull final byte[] bytes) {
        return intFrom3Bytes(bytes, 0);
    }

    public static byte[] intTo4Bytes(final int value) {
        return new byte[]{
                (byte) ((value >>> 24) & MASK),
                (byte) ((value >>> 16) & MASK),
                (byte) ((value >>> 8) & MASK),
                (byte) (value & MASK)
        };
    }

    public static int intFrom4Bytes(@NonNull final byte[] bytes) {
        return intFrom4Bytes(bytes, 0);
    }

    public static int intFrom4Bytes(@NonNull final byte[] bytes, final int startIndex) {
        return ((bytes[startIndex] & MASK) << 24)
                |
                ((bytes[startIndex + 1] & MASK) << 16)
                |
                ((bytes[startIndex + 2] & MASK) << 8)
                |
                ((bytes[startIndex + 3] & MASK));
    }

    public static int intFromBytes(@NonNull final byte[] bytes, int start, int len) {
        switch (len) {
            case 1:
                return intFrom1Byte(bytes, start);
            case 2:
                return intFrom2Bytes(bytes, start);
            case 3:
                return intFrom3Bytes(bytes, start);
            case 4:
                return intFrom4Bytes(bytes, start);
            default: {
                throw new IllegalArgumentException("len ∈ [1,4]");
            }
        }
    }

    public static int intFromBytes(@NonNull final byte[] bytes) {
        return intFromBytes(bytes, 0, bytes.length);
    }

}
