package io.github.hylexus.utils;

import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
public abstract class Numbers {

    public static boolean isOdd(final int number) {
        return (number & 1) != 0;
    }

    public static boolean isEven(final int number) {
        return (number & 1) == 0;
    }

    public static boolean isPositive(final Number n) {
        return n != null && n.doubleValue() > 0;
    }

    public static boolean isNegative(final Number n) {
        return n != null && n.doubleValue() < 0;
    }

    public static Optional<Integer> parseInteger(final Object object) {
        return parseInteger(object.toString());
    }

    public static Optional<Integer> parseInteger(final String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> parseLong(final Object object) {
        return parseLong(object.toString());
    }

    public static Optional<Long> parseLong(final String string) {
        try {
            return Optional.of(Long.parseLong(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Float> parseFloat(final Object object) {
        return parseFloat(object.toString());
    }

    public static Optional<Float> parseFloat(final String string) {
        try {
            return Optional.of(Float.parseFloat(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> parseDouble(final Object object) {
        return parseDouble(object.toString());
    }

    public static Optional<Double> parseDouble(final String string) {
        try {
            return Optional.of(Double.parseDouble(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static int getBitAt(final long value, final int index) {
        return (int) (((1 << index) & value) >> index);
    }

    public static int getBitAt(final int value, final int index) {
        return (((1 << index) & value) >> index);
    }

    public static int setBitAt(final int value, final int index) {
        return (1 << index) | value;
    }

    public static long setBitAt(final long value, final int index) {
        return (1 << index) | value;
    }

    public static int resetBitAt(final int value, final int index) {
        return value & ~(1 << index);
    }

    public static long resetBitAt(final long value, final int index) {
        return value & ~(1 << index);
    }

    public static int setBitAt(final int value, final int index, final boolean flag) {
        return flag ? setBitAt(value, index) : resetBitAt(value, index);
    }

    public static long setBitAt(final long value, final int index, final boolean flag) {
        return flag ? setBitAt(value, index) : resetBitAt(value, index);
    }

    public static int getBitRangeAsInt(final int number, final int start, final int end) {
        return (number << Integer.SIZE - (end + 1)) >>> Integer.SIZE - (end - start + 1);
    }

    public static long getBitRangeAsLong(final long number, final int start, final int end) {
        return (number << Long.SIZE - (end + 1)) >>> Long.SIZE - (end - start + 1);
    }
}
