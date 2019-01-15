package cn.oaks.utils;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author hylexus
 * createdAt 2019/1/14
 **/
public class IntBitOpsTest {

    @Test
    public void intTo1Byte() {
        assertArrayEquals(new byte[]{1}, IntBitOps.intTo1Byte(1));
        assertArrayEquals(new byte[]{127}, IntBitOps.intTo1Byte(127));
        assertArrayEquals(new byte[]{Byte.MIN_VALUE}, IntBitOps.intTo1Byte(128));
        assertArrayEquals(new byte[]{Byte.MIN_VALUE + 1}, IntBitOps.intTo1Byte(129));
    }

    @Test
    public void intToByte() {
        assertEquals(1, IntBitOps.intToByte(1));
        assertEquals(Byte.MAX_VALUE, IntBitOps.intToByte(127));
        assertEquals(Byte.MIN_VALUE, IntBitOps.intToByte(128));
    }

    @Test
    public void intTo2Bytes() {
        assertArrayEquals(new byte[]{0, 1}, IntBitOps.intTo2Bytes(1));
        assertArrayEquals(new byte[]{0, Byte.MAX_VALUE}, IntBitOps.intTo2Bytes(127));
        assertArrayEquals(new byte[]{0, Byte.MIN_VALUE}, IntBitOps.intTo2Bytes(128));
    }


    @Test
    public void intFrom2Bytes() {
        assertEquals(1, IntBitOps.intFrom2Bytes(new byte[]{0, 1}));
        assertEquals(127, IntBitOps.intFrom2Bytes(new byte[]{0, Byte.MAX_VALUE}));
        assertEquals(128, IntBitOps.intFrom2Bytes(new byte[]{0, Byte.MIN_VALUE}));
    }

    @Test
    public void intTo3Bytes() {
        assertArrayEquals(new byte[]{0, 0, 1}, IntBitOps.intTo3Bytes(1));
        assertArrayEquals(new byte[]{0, 0, Byte.MAX_VALUE}, IntBitOps.intTo3Bytes(0b0111_1111)); // 127
        assertArrayEquals(new byte[]{0, 0, Byte.MIN_VALUE}, IntBitOps.intTo3Bytes(128));
        assertArrayEquals(new byte[]{0, 1, 0}, IntBitOps.intTo3Bytes(0b1_0000_0000)); // 256
        assertArrayEquals(new byte[]{1, 0, 0}, IntBitOps.intTo3Bytes(0b0001_0000_0000_0000_0000)); // 65536
    }


    @Test
    public void intFrom3Bytes() {
        assertEquals(1, IntBitOps.intFrom3Bytes(new byte[]{0, 0, 1}));
        assertEquals(127, IntBitOps.intFrom3Bytes(new byte[]{0, 0, Byte.MAX_VALUE}));
        assertEquals(128, IntBitOps.intFrom3Bytes(new byte[]{0, 0, Byte.MIN_VALUE}));
        assertEquals(0x01_00, IntBitOps.intFrom3Bytes(new byte[]{0, 1, 0}));
        assertEquals(0x01_00_00, IntBitOps.intFrom3Bytes(new byte[]{1, 0, 0}));
    }

    @Test
    public void intTo4Bytes() {
        assertArrayEquals(new byte[]{0, 0, 0, 0}, IntBitOps.intTo4Bytes(0));
        assertArrayEquals(new byte[]{0, 0, 0, 1}, IntBitOps.intTo4Bytes(1));
        assertArrayEquals(new byte[]{0, 0, 0, Byte.MAX_VALUE}, IntBitOps.intTo4Bytes(0b0111_1111)); // 127
        assertArrayEquals(new byte[]{0, 0, 0, Byte.MIN_VALUE}, IntBitOps.intTo4Bytes(128));
        assertArrayEquals(new byte[]{0, 0, 1, 0}, IntBitOps.intTo4Bytes(0x01_00)); // 256
        assertArrayEquals(new byte[]{0, 1, 0, 0}, IntBitOps.intTo4Bytes(0x01_00_00)); // 65536
        assertArrayEquals(new byte[]{1, 0, 0, 0}, IntBitOps.intTo4Bytes(0x01_00_00_00)); // 16777216
        assertArrayEquals(new byte[]{1, 1, 1, 1}, IntBitOps.intTo4Bytes(0b0001_0000_0001_0000_0001_0000_0001)); // 16843009
        assertArrayEquals(new byte[]{Byte.MAX_VALUE, -1, -1, -1}, IntBitOps.intTo4Bytes(0x7f_ff_ff_ff)); // Integer.MAX_VALUE
        assertArrayEquals(new byte[]{Byte.MIN_VALUE, 0, 0, 0}, IntBitOps.intTo4Bytes(0x80_00_00_00)); // Integer.MIN_VALUE
    }


    @Test
    public void intFrom4Bytes() {
        assertEquals(0, IntBitOps.intFrom4Bytes(new byte[]{0, 0, 0, 0}));
        assertEquals(1, IntBitOps.intFrom4Bytes(new byte[]{0, 0, 0, 1}));
        assertEquals(127, IntBitOps.intFrom4Bytes(new byte[]{0, 0, 0, Byte.MAX_VALUE}));
        assertEquals(128, IntBitOps.intFrom4Bytes(new byte[]{0, 0, 0, Byte.MIN_VALUE}));
        assertEquals(0x01_00_00, IntBitOps.intFrom4Bytes(new byte[]{0, 1, 0, 0}));
        assertEquals(0x01_00_00_00, IntBitOps.intFrom4Bytes(new byte[]{1, 0, 0, 0}));
        assertEquals(0x01_01_01_01, IntBitOps.intFrom4Bytes(new byte[]{1, 1, 1, 1}));
        assertEquals(0x7F_FF_FF_FF, IntBitOps.intFrom4Bytes(new byte[]{Byte.MAX_VALUE, -1, -1, -1}));
        assertEquals(0x80_00_00_00, IntBitOps.intFrom4Bytes(new byte[]{Byte.MIN_VALUE, 0, 0, 0}));
    }

    @Test
    public void intFrom1Byte() {
        assertEquals(1, IntBitOps.intFrom1Byte((byte) 1));
        assertEquals(127, IntBitOps.intFrom1Byte((byte) 127));
        assertEquals(128, IntBitOps.intFrom1Byte((byte) 128));
        assertEquals(0xFF, IntBitOps.intFrom1Byte((byte) 0xFF));
        assertEquals(0xFF, IntBitOps.intFrom1Byte((byte) 0xFFFF));
    }

    @Test
    public void intFromBytes() {
        assertEquals(0x01_00_00_00, IntBitOps.intFromBytes(new byte[]{1, 0, 0, 0}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void intFromBytesIllegalArgument() {
        IntBitOps.intFromBytes(new byte[]{1, 0, 0, 0, 0});
    }
}