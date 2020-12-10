# Module la4k-log4j2

The `la4k-log4j2` bridge connects `la4k-api` to the excellent Apache Log4j engine. Only version
2 is supported as version 1 has been discontinued.

As LA4K's levels were modeled after those from Log4j, no level conversion takes place.

LA4K tags become standalone Log4j markers, which are cached for each `org.la4k.Logger` instance
by this bridge.
