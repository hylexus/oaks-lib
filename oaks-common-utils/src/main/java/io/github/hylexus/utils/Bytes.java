package io.github.hylexus.utils;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author hylexus
 * createdAt 2019/1/15
 **/
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class Bytes {

    private static Predicate<byte[]> NOT_EMPTY_BYTE_ARRAY = bytes -> bytes != null && bytes.length > 0;

    public static byte[] concatAll(@NonNull final byte[] first, @NonNull final byte[]... rest) {
        return concatAll(first, NOT_EMPTY_BYTE_ARRAY, rest);
    }

    public static byte[] concatAll(@NonNull final byte[] first, Predicate<byte[]> predicate, @NonNull final byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            if (predicate.test(array)) {
                totalLength += array.length;
            }
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            if (predicate.test(array)) {
                System.arraycopy(array, 0, result, offset, array.length);
                offset += array.length;
            }
        }
        return result;
    }

    public static byte[] concatAll(@NonNull final List<byte[]> rest) {
        return concatAll(rest, NOT_EMPTY_BYTE_ARRAY);
    }

    public static byte[] concatAll(@NonNull final List<byte[]> rest, Predicate<byte[]> predicate) {
        int totalLength = 0;
        for (byte[] array : rest) {
            if (predicate.test(array)) {
                totalLength += array.length;
            }
        }
        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] array : rest) {
            if (predicate.test(array)) {
                System.arraycopy(array, 0, result, offset, array.length);
                offset += array.length;
            }
        }
        return result;
    }

    public static byte[] range(@NonNull final byte[] bytes, final int start, final int end) {
        assert start < end : "start < end";
        return subSequence(bytes, start, end - start);
    }

    public static byte[] subSequence(@NonNull final byte[] bytes, final int startIndex, final int length) {
        final byte[] tmp = new byte[length];
        System.arraycopy(bytes, startIndex, tmp, 0, length);
        return tmp;
    }
}
