package cn.oaks.utils;

/**
 * @author hylexus
 * createdAt 2019/1/15
 **/
public abstract class FloatBitOps {
    private static final int MASK = 0xFF;

    public static float floatFrom4Bytes(byte[] bytes) {
        int i = (bytes[0] & MASK)
                | ((bytes[1] & MASK) << 8)
                | ((bytes[2] & MASK) << 16)
                | ((bytes[3] & MASK) << 24);
        return Float.intBitsToFloat(i);
    }

}
