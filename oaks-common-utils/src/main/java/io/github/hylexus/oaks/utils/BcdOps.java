package io.github.hylexus.oaks.utils;

import lombok.NonNull;

/**
 * @author hylexus
 * createdAt 2019/7/7
 */
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class BcdOps {

    public static String bytes2BcdString(@NonNull final byte[] bytes, final int start, final int length) {
        return bcd2String(bytes, start, start + length);
    }

    public static String bcd2String(final byte[] bytes, final int start, final int end) {

        assert start < end : "start < end";

        final int length = end - start;
        final StringBuilder builder = new StringBuilder(length << 2);
        for (int i = start; i < end; i++) {
            builder.append((bytes[i] & 0xf0) >>> 4);
            builder.append(bytes[i] & 0x0f);
        }

        return builder.toString();
    }

    /**
     * bcd bytes to String
     * <p>
     * A details about BCD(Binary-Coded Decimal) : https://zn.wikipedia.org/wiki/Binary-coded_decimal
     *
     * @param bytes a bytes array based on BCD(Binary-Coded Decimal)
     * @return Hexadecimal string
     */
    public static String bcd2String(final byte[] bytes) {
        return bcd2String(bytes, 0, bytes.length);
    }

    /**
     * A alias for {@link #string2Bcd(String)}
     *
     * @param str a String encoded by BCD
     * @return bytes array based on BCD
     */
    public static byte[] bcdString2bytes(final String str) {
        return string2Bcd(str);
    }

    /**
     * String to bytes array based on BCD(Binary-Coded Decimal)
     *
     * @param str a String encoded by BCD
     * @return bytes array based on BCD
     */
    public static byte[] string2Bcd(final String str) {
        // odd -> 0 + str
        final String source = Numbers.isOdd(str.length())
                ? "0" + str
                : str;

        final byte[] result = new byte[source.length() >> 1];
        final byte[] sourceBytes = source.getBytes();
        for (int i = 0; i < result.length; i++) {

            byte high = ascii2Bcd(sourceBytes[i << 1]);
            byte low = ascii2Bcd(sourceBytes[(i << 1) + 1]);

            result[i] = (byte) ((high << 4) | low);
        }
        return result;
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private static byte ascii2Bcd(final byte asc) {
        if ((asc >= '0') && (asc <= '9')) {
            return (byte) (asc - '0');
        } else if ((asc >= 'A') && (asc <= 'F')) {
            return (byte) (asc - 'A' + 10);
        } else if ((asc >= 'a') && (asc <= 'f')) {
            return (byte) (asc - 'a' + 10);
        } else {
            return (byte) (asc - 48);
        }
    }
}
