package org.la4k.api

public class Logger(val name: String) {

    public fun off(message: String) = log(name, Level.off, message)

    public fun fatal(message: String) = log(name, Level.fatal, message)

    public fun error(message: String) = log(name, Level.error, message)

    public fun warn(message: String) = log(name, Level.warn, message)

    public fun info(message: String) = log(name, Level.info, message)

    public fun debug(message: String) = log(name, Level.debug, message)

    public fun trace(message: String) = log(name, Level.trace, message)

    public fun all(message: String) = log(name, Level.all, message)

    public fun custom(level: Level, message: String) = log(name, level, message)
}
