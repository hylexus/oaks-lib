package io.github.hylexus.oaks.utils;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
public class NumbersTest {

    @Test
    public void isPositive() {
        assertTrue(Numbers.isPositive(1));
        assertTrue(Numbers.isPositive(1.1F));
        assertTrue(Numbers.isPositive(1.1D));
        assertTrue(Numbers.isPositive(1L));

        assertFalse(Numbers.isPositive(null));
        assertFalse(Numbers.isPositive(-1));
    }

    @Test
    public void isNegative() {
        assertTrue(Numbers.isNegative(-1));
        assertTrue(Numbers.isNegative(-1.1D));
        assertTrue(Numbers.isNegative(-1.2F));
        assertTrue(Numbers.isNegative(-1L));
        assertFalse(Numbers.isNegative(0));
        assertFalse(Numbers.isNegative(null));
        assertFalse(Numbers.isNegative(1));
    }

    @Test
    public void parseInteger() {
        assertEquals(Optional.of(1), Numbers.parseInteger("1"));
        assertEquals(Optional.empty(), Numbers.parseInteger("1.2"));
        assertEquals(Optional.empty(), Numbers.parseInteger("abc"));
        assertEquals(Optional.empty(), Numbers.parseInteger(null));
    }


    @Test
    public void parseLong() {
        assertEquals(Optional.of(1L), Numbers.parseLong("1"));
        assertEquals(Optional.empty(), Numbers.parseLong("1.2"));
        assertEquals(Optional.empty(), Numbers.parseLong("abc"));
        assertEquals(Optional.empty(), Numbers.parseLong(null));
    }

    @Test
    public void parseFloat() {
        assertEquals(Optional.of(1.1F), Numbers.parseFloat("1.1"));
        assertEquals(Optional.of(1.2F), Numbers.parseFloat("1.2"));
        assertEquals(Optional.empty(), Numbers.parseFloat("abc"));
        assertEquals(Optional.empty(), Numbers.parseFloat(null));
    }

    @Test
    public void parseDouble() {
        assertEquals(Optional.of(1.1D), Numbers.parseDouble("1.1"));
        assertEquals(Optional.of(1.2D), Numbers.parseDouble("1.2"));
        assertEquals(Optional.empty(), Numbers.parseDouble("abc"));
        assertEquals(Optional.empty(), Numbers.parseDouble(null));
    }

    @Test
    public void getBitAtInt() {
        assertEquals(0, Numbers.getBitAt(0b1010, 0));
        assertEquals(1, Numbers.getBitAt(0b1010, 1));
        assertEquals(0, Numbers.getBitAt(0b1010, 2));
    }

    @Test
    public void getBitAtLong() {
        assertEquals(1, Numbers.getBitAt(1L, 0));
        assertEquals(0, Numbers.getBitAt(1L, 1));
        assertEquals(0, Numbers.getBitAt(3L, 2));
        assertEquals(1, Numbers.getBitAt(3L, 1));
        assertEquals(1, Numbers.getBitAt(4611686087146864640L, 62));
        assertEquals(1, Numbers.getBitAt(4611686087146864640L, 36));
    }

    @Test
    public void setBitAtInt() {
        assertEquals(0b1011, Numbers.setBitAt(0b1010, 0));
        assertEquals(0b1010, Numbers.setBitAt(0b1010, 1));
        final int i = Numbers.setBitAt(0, 31);
        assertEquals(1, Numbers.getBitAt(i, 31));
    }

    @Test
    public void setBitAtLong() {
        assertEquals(0b1011, Numbers.setBitAt(0b1010, 0));
        assertEquals(0b1010, Numbers.setBitAt(0b1010, 1));
    }

    @Test
    public void resetBitAtInt() {
        assertEquals(0b1010, Numbers.resetBitAt(0b1010, 0));
        assertEquals(0b1000, Numbers.resetBitAt(0b1010, 1));
    }

    @Test
    public void resetBitAtLong() {
        assertEquals(0, Numbers.resetBitAt(1L, 0));
        assertEquals(1, Numbers.resetBitAt(1L, 1));
        assertEquals(-9223372036854775808L, Numbers.resetBitAt(-4611686018427387904L, 62));
    }

    @Test
    public void setBitAtWithValueInt() {
        assertEquals(0b1011, Numbers.setBitAt(0b1010, 0, true));
        assertEquals(0b1010, Numbers.setBitAt(0b1010, 0, false));
    }

    @Test
    public void setBitAtWithValueLong() {
        assertEquals(1, Numbers.setBitAt(1L, 0, true));
        assertEquals(0, Numbers.setBitAt(1L, 0, false));
        assertEquals(0b10, Numbers.setBitAt(2L, 0, false));
        assertEquals(0b11, Numbers.setBitAt(2L, 0, true));
        assertEquals(4611686018427387904L, Numbers.setBitAt(0L, 62, true));
        assertEquals(1, Numbers.getBitAt(4611686018427387904L, 62));
    }

    @Test
    public void getBitRangeAsInt() {
        assertEquals(0b101, Numbers.getBitRangeAsInt(0b101011101, 0, 2));
    }

    @Test
    public void getBitRangeAsLong() {
        assertEquals(0b1011101, Numbers.getBitRangeAsLong(0b101011101, 0, 6));
    }
}
