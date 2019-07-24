/*
 * Copyright 2012-2017 the original author or authors.
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

package io.github.hylexus.oaks.ansi;
/*
 * Copyright 2012-2017 the original author or authors.
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

import lombok.Getter;

/**
 * See https://en.wikipedia.org/wiki/ANSI_escape_code#3/4_bit
 * <p>
 * Copied from org.springframework.boot.ansi.AnsiBackground
 *
 * @author Phillip Webb
 * @author Geoffrey Chandler
 */
@Getter
public enum AnsiBackgroundColor implements AnsiEscapeCode {
    BLACK("40"),
    RED("41"),
    GREEN("42"),
    YELLOW("43"),
    BLUE("44"),
    MAGENTA("45"),
    CYAN("46"),
    WHITE("47"),

    BRIGHT_BLACK("100"),
    BRIGHT_RED("101"),
    BRIGHT_GREEN("102"),
    BRIGHT_YELLOW("103"),
    BRIGHT_BLUE("104"),
    BRIGHT_MAGENTA("105"),
    BRIGHT_CYAN("106"),
    BRIGHT_WHITE("107"),

    DEFAULT_BACKGROUND_COLOR("49"),
    ;

    private String ansiCode;

    AnsiBackgroundColor(String code) {
        this.ansiCode = code;
    }
}
