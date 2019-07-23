package io.github.hylexus.oaks.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author hylexus
 * createdAt 2019/1/15
 **/
public class FloatBitOpsTest {

    @Test
    public void floatFrom4Bytes() {
//        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(-2.689F).array();
        assertEquals(-2.6890F, FloatBitOps.floatFrom4Bytes(new byte[]{-109, 24, 44, -64}), 0);

        assertEquals(-2.6890F, FloatBitOps.floatFrom4Bytes(new byte[]{-109, 24, 44, -64}, 0), 0);

        assertEquals(-2.6890F, FloatBitOps.floatFrom4Bytes(new byte[]{-1, -1, 1, -109, 24, 44, -64}, 3), 0);
    }
}