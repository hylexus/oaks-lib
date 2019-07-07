package io.github.hylexus.utils;

/**
 * @author hylexus
 * createdAt 2019/7/7
 */
@SuppressWarnings("AlibabaAbstractClassShouldStartWithAbstractNaming")
public abstract class BcdOps {

    public static String bcd2String(final byte[] bytes, final int start, final int end) {

        assert start < end : "start < end";

        final int length = end - start;
        final StringBuilder builder = new StringBuilder(length << 2);
        for (int i = start; i < end; i++) {
            builder.append((bytes[i] & 0xf0) >>> 4);
            builder.append(bytes[i] & 0x0f);
        }

        return "0".equalsIgnoreCase(builder.toString().substring(0, 1))
                ? builder.toString().substring(1)
                : builder.toString();
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
