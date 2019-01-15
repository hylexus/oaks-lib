package cn.oaks.utils;

import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
public abstract class Numbers {

    public static boolean isPositive(Number n) {
        return n != null && n.doubleValue() > 0;
    }

    public static boolean isNegative(Number n) {
        return n != null && n.doubleValue() < 0;
    }

    public static Optional<Integer> parseInteger(Object object) {
        return parseInteger(object.toString());
    }

    public static Optional<Integer> parseInteger(String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> parseLong(Object object) {
        return parseLong(object.toString());
    }

    public static Optional<Long> parseLong(String string) {
        try {
            return Optional.of(Long.parseLong(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Float> parseFloat(Object object) {
        return parseFloat(object.toString());
    }

    public static Optional<Float> parseFloat(String string) {
        try {
            return Optional.of(Float.parseFloat(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Double> parseDouble(Object object) {
        return parseDouble(object.toString());
    }

    public static Optional<Double> parseDouble(String string) {
        try {
            return Optional.of(Double.parseDouble(string));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static int getBitAt(long value, int index) {
        return (int) (((1 << index) & value) >> index);
    }

    public static int getBitAt(int value, int index) {
        return (((1 << index) & value) >> index);
    }

    public static int setBitAt(int value, int index) {
        return (1 << index) | value;
    }

    public static long setBitAt(long value, int index) {
        return (1 << index) | value;
    }

    public static int resetBitAt(int value, int index) {
        return value & ~(1 << index);
    }

    public static long resetBitAt(long value, int index) {
        return value & ~(1 << index);
    }

    public static int setBitAt(int value, int index, boolean flag) {
        return flag ? setBitAt(value, index) : resetBitAt(value, index);
    }

    public static long setBitAt(long value, int index, boolean flag) {
        return flag ? setBitAt(value, index) : resetBitAt(value, index);
    }

    public static int getBitRangeAsInt(int number, int start, int end) {
        return (number << Integer.SIZE - (end + 1)) >>> Integer.SIZE - (end - start + 1);
    }

    public static long getBitRangeAsLong(long number, int start, int end) {
        return (number << Long.SIZE - (end + 1)) >>> Long.SIZE - (end - start + 1);
    }
}
