package org.la4k.api

public expect class Logger(name: String) {

    public fun fatal(message: String)

    companion object {

        public fun reload()
    }
}
