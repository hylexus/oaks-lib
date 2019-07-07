package io.github.hylexus.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author hylexus
 * createdAt 2019/1/15
 **/
public class BytesTest {

    @Test
    public void concatAll() {
        byte[] bytes1 = new byte[]{1, 2, 3};
        byte[] bytes2 = new byte[]{1, 2, 3};
        assertArrayEquals(new byte[]{1, 2, 3, 1, 2, 3}, Bytes.concatAll(bytes1, bytes2));
    }

    @Test(expected = Exception.class)
    public void concatAllNPE() {
        Bytes.concatAll(null);
    }

    @Test
    public void concatAllList() {
        List<byte[]> list = new ArrayList<>();
        list.add(new byte[]{1, 2, 3});
        list.add(new byte[]{1, 2, 3});
        byte[] result = Bytes.concatAll(list);
        assertArrayEquals(new byte[]{1, 2, 3, 1, 2, 3}, result);
    }

    @Test
    public void subSequence() {
        byte[] bytes = {1, 2, 3, 4};
        assertArrayEquals(new byte[]{3}, Bytes.subSequence(bytes, 2, 1));
        assertArrayEquals(new byte[]{2, 3}, Bytes.subSequence(bytes, 1, 2));
        assertArrayEquals(new byte[]{1}, Bytes.subSequence(bytes, 0, 1));
        assertArrayEquals(new byte[]{4}, Bytes.subSequence(bytes, 3, 1));
        assertArrayEquals(new byte[]{}, Bytes.subSequence(bytes, 3, 0));
    }

    @Test
    public void range() {
        byte[] bytes = {1, 2, 3, 4};
        assertArrayEquals(new byte[]{3}, Bytes.range(bytes, 2, 3));
    }
}