package io.github.hylexus.oaks.ansi;

/**
 * @author hylexus
 * Created At 2019-07-22 21:54
 */
@FunctionalInterface
public interface AnsiEscapeCode {

    String PREFIX = "\033[";

    String SUFFIX = "m";

    String SEPARATOR = ";";

    String getAnsiCode();

    AnsiEscapeCode RESET = () -> "0;39";
}
