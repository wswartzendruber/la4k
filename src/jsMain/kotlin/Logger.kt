package org.la4k.api

public actual class Logger actual constructor(name: String) {

    public actual fun fatal(message: String) { }

    actual companion object {

        public actual fun reload() { }
    }
}