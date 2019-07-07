package io.github.hylexus.utils;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author hylexus
 * Created At 2019-07-07 15:10
 */
public class BcdOpsTest {

    @Test
    public void bcd2String() {
        byte[] bytes = {118, -119, 1, 0, 83, 96};
        assertEquals("768901005360", BcdOps.bcd2String(bytes));
        assertEquals("768901005360", BcdOps.bcd2String(bytes, 0, bytes.length));

        byte[] bytes1 = {1, 1, 1, 118, -119, 1, 0, 83, 96, 1, 1};
        assertEquals("768901005360", BcdOps.bcd2String(bytes1, 3, bytes1.length - 2));

        assertEquals("00000000000", BcdOps.bcd2String(new byte[]{0, 0, 0, 0, 0, 0}));
        byte[] bytes2 = {1, 1, 0, 0, 0, 0, 0, 0, 1, 1};
        assertEquals("00000000000", BcdOps.bcd2String(bytes2, 2, bytes2.length - 2));
    }

    @Test(expected = AssertionError.class)
    public void bcd2StringE() {
        byte[] bytes = {118, -119, 1, 0, 83, 96};
        assertEquals("768901005360", BcdOps.bcd2String(bytes, 0, 0));
    }

    @Test
    public void string2Bcd() {
        assertArrayEquals(new byte[]{118, -119, 1, 0, 83, 96}, BcdOps.string2Bcd("768901005360"));
        assertArrayEquals(new byte[]{0, 0, 0, 0, 0, 0}, BcdOps.string2Bcd("00000000000"));
    }
}