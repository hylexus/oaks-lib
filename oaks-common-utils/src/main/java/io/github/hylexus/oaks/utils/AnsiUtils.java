/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hylexus.oaks.utils;

import io.github.hylexus.oaks.ansi.AnsiEscapeCode;

import java.util.Locale;

/**
 * Main logic was copied from org.springframework.boot.ansi.AnsiOutput#buildEnabled
 *
 * @author hylexus
 * Created At 2019-07-22 21:46
 */
public abstract class AnsiUtils {

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

    public static String buildString(Object... objects) {
        return buildString(true, objects);
    }

    /**
     * See org.springframework.boot.ansi.AnsiOutput#buildEnabled
     *
     * @author Phillip Webb (original author of spring-boot's AnsiOutput#buildEnabled method)
     * @author hylexus
     */
    public static String buildString(boolean resetAllAnsiPropsAfterReturning, Object... objects) {
        StringBuilder sb = new StringBuilder();
        boolean ansiPrefixAppended = false;
        boolean ansiOccurred = false;
        for (Object obj : objects) {
            if (obj == null) {
                continue;
            }
            if (obj instanceof AnsiEscapeCode) {
                ansiOccurred = true;

                if (ansiPrefixAppended) {
                    sb.append(AnsiEscapeCode.SEPARATOR);
                } else {
                    sb.append(AnsiEscapeCode.PREFIX);
                    ansiPrefixAppended = true;
                }

                sb.append(((AnsiEscapeCode) obj).getAnsiCode());
            } else {
                if (ansiPrefixAppended) {
                    sb.append(AnsiEscapeCode.SUFFIX);
                    ansiPrefixAppended = false;
                }
                sb.append(obj);
            }
        }

        if (resetAllAnsiPropsAfterReturning && ansiOccurred) {
            if (ansiPrefixAppended) {
                sb.append(AnsiEscapeCode.SEPARATOR);
            } else {
                sb.append(AnsiEscapeCode.PREFIX);
            }
            sb.append(AnsiEscapeCode.RESET).append(AnsiEscapeCode.SUFFIX);
        }
        return sb.toString();
    }

}
