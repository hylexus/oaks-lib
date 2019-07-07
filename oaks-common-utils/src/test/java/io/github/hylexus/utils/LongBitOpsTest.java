package io.github.hylexus.utils;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
public class LongBitOpsTest {

    @Test
    public void longTo1Byte() {
        assertArrayEquals(new byte[]{1}, LongBitOps.longTo1Byte(1));
        assertArrayEquals(new byte[]{127}, LongBitOps.longTo1Byte(127));
        assertArrayEquals(new byte[]{Byte.MIN_VALUE}, LongBitOps.longTo1Byte(128));
        assertArrayEquals(new byte[]{Byte.MIN_VALUE + 1}, LongBitOps.longTo1Byte(129));
    }

    @Test
    public void longToByte() {
        assertEquals(1, LongBitOps.longToByte(1));
        assertEquals(127, LongBitOps.longToByte(127));
        assertEquals(Byte.MIN_VALUE, LongBitOps.longToByte(128));
        assertEquals(Byte.MIN_VALUE + 1, LongBitOps.longToByte(129));
    }

    @Test
    public void longTo8Bytes() {
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 85, 34, 51}, LongBitOps.longTo8Bytes(0x55_22_33));
    }

    @Test
    public void longFrom8Bytes() {
        assertEquals(0x55_22_33, LongBitOps.longFrom8Bytes(new byte[]{0, 0, 0, 0, 0, 85, 34, 51}));
        assertEquals(0x55_22_33, LongBitOps.longFrom8Bytes(new byte[]{1, 1, 1, 0, 0, 0, 0, 0, 85, 34, 51}, 3));

        assertEquals(Long.MAX_VALUE, LongBitOps.longFrom8Bytes(new byte[]{127, -1, -1, -1, -1, -1, -1, -1}));
        assertEquals(Long.MAX_VALUE, LongBitOps.longFrom8Bytes(new byte[]{1, -1, 127, -1, -1, -1, -1, -1, -1, -1}, 2));

        assertEquals(Long.MIN_VALUE, LongBitOps.longFrom8Bytes(new byte[]{-128, 0, 0, 0, 0, 0, 0, 0}));
        assertEquals(Long.MIN_VALUE, LongBitOps.longFrom8Bytes(new byte[]{1, 1, 0, 0, -128, 0, 0, 0, 0, 0, 0, 0}, 4));

        assertEquals(271828456L, LongBitOps.longFrom8Bytes(new byte[]{0, 0, 0, 0, 16, 51, -59, -24}));
        assertEquals(271828456L, LongBitOps.longFrom8Bytes(new byte[]{1, 1, 9, 0, 0, 0, 0, 16, 51, -59, -24}, 3));
    }
}