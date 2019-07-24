package io.github.hylexus.oaks.utils;

import io.github.hylexus.oaks.ansi.AnsiForegroundColor;
import org.junit.Test;

import static io.github.hylexus.oaks.ansi.AnsiForegroundColor.BRIGHT_BLUE;
import static io.github.hylexus.oaks.ansi.AnsiForegroundColor.RED;

/**
 * @author hylexus
 * Created At 2019-07-22 21:47
 */
public class AnsiUtilsTest {

    @Test
    public void test1() {
        System.out.println("\033[33;4mc\033[31;4mon\033[32mtent\033[31m");
    }

    @Test
    public void buildStringTest() {
        String string = AnsiUtils.buildString(RED, "hello", BRIGHT_BLUE, "world", AnsiForegroundColor.MAGENTA, "!");
        System.out.println(string);
    }

}