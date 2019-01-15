package cn.oaks.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void concatAllList() {
        List<byte[]> list = new ArrayList<>();
        list.add(new byte[]{1, 2, 3});
        list.add(new byte[]{1, 2, 3});
        byte[] result = Bytes.concatAll(list);
        assertArrayEquals(new byte[]{1, 2, 3, 1, 2, 3}, result);
    }
}