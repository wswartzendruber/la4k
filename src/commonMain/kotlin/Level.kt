package org.la4k

public class Level(val name: String, val value: Int) {

    public companion object {

        public val off = Level("OFF", 0)

        public val fatal = Level("FATAL", 0)

        public val error = Level("ERROR", 0)

        public val warn = Level("WARN", 0)

        public val info = Level("INFO", 0)

        public val debug = Level("DEBUG", 0)

        public val trace = Level("TRACE", 0)

        public val all = Level("ALL", 0)
    }
}
