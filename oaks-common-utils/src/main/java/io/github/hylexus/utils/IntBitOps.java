package io.github.hylexus.utils;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
public abstract class IntBitOps {

    private static final int MASK = 0xFF;

    public static byte intToByte(int i) {
        return (byte) (i & MASK);
    }

    public static byte[] intTo1Byte(int i) {
        return new byte[]{intToByte(i)};
    }


    public static int intFrom1Byte(byte b) {
        return b & MASK;
    }

    public static byte[] intTo2Bytes(int value) {
        return new byte[]{
                (byte) ((value >>> 8) & MASK),
                (byte) (value & MASK)
        };
    }


    public static int intFrom2Bytes(byte[] bytes) {
        return ((bytes[0] & MASK) << 8)
                |
                (bytes[1] & MASK);
    }

    public static byte[] intTo3Bytes(int value) {
        return new byte[]{
                (byte) ((value >>> 16) & MASK),
                (byte) ((value >>> 8) & MASK),
                (byte) (value & MASK)
        };
    }

    public static int intFrom3Bytes(byte[] bytes) {
        return ((bytes[0] & MASK) << 16)
                |
                ((bytes[1] & MASK) << 8)
                |
                ((bytes[2] & MASK));
    }

    public static byte[] intTo4Bytes(int value) {
        return new byte[]{
                (byte) ((value >>> 24) & MASK),
                (byte) ((value >>> 16) & MASK),
                (byte) ((value >>> 8) & MASK),
                (byte) (value & MASK)
        };
    }

    public static int intFrom4Bytes(byte[] bytes) {
        return ((bytes[0] & MASK) << 24)
                |
                ((bytes[1] & MASK) << 16)
                |
                ((bytes[2] & MASK) << 8)
                |
                ((bytes[3] & MASK));
    }

    public static int intFromBytes(byte[] bytes) {
        switch (bytes.length) {
            case 1:
                return intFrom1Byte(bytes[0]);
            case 2:
                return intFrom2Bytes(bytes);
            case 3:
                return intFrom3Bytes(bytes);
            case 4:
                return intFrom4Bytes(bytes);
            default:
                throw new IllegalArgumentException("bytes length ∈ [1,4]");
        }
    }

}
