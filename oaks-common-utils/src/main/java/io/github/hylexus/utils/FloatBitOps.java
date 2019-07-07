package io.github.hylexus.utils;

import lombok.NonNull;

/**
 * @author hylexus
 * createdAt 2019/1/15
 **/
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class FloatBitOps {
    private static final int MASK = 0xFF;

    public static float floatFrom4Bytes(@NonNull final byte[] bytes) {
        return floatFrom4Bytes(bytes, 0);
    }

    public static float floatFrom4Bytes(@NonNull final byte[] bytes, final int startIndex) {
        int i = (bytes[startIndex] & MASK)
                | ((bytes[startIndex + 1] & MASK) << 8)
                | ((bytes[startIndex + 2] & MASK) << 16)
                | ((bytes[startIndex + 3] & MASK) << 24);
        return Float.intBitsToFloat(i);
    }

}
