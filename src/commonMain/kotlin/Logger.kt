package org.la4k.api

public class Logger(val name: String) {

    public fun off(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.off, message, throwable, tags)

    public fun <R> off(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.off, block, throwable, tags)

    public fun fatal(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.fatal, message, throwable, tags)

    public fun <R> fatal(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.fatal, block, throwable, tags)

    public fun error(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.error, message, throwable, tags)

    public fun <R> error(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.error, block, throwable, tags)

    public fun warn(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.warn, message, throwable, tags)

    public fun <R> warn(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.warn, block, throwable, tags)

    public fun info(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.info, message, throwable, tags)

    public fun <R> info(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.info, block, throwable, tags)

    public fun debug(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.debug, message, throwable, tags)

    public fun <R> debug(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.debug, block, throwable, tags)

    public fun trace(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.trace, message, throwable, tags)

    public fun <R> trace(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.trace, block, throwable, tags)

    public fun all(
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.all, message, throwable, tags)

    public fun <R> all(
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, Level.all, block, throwable, tags)

    public fun custom(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, level, message, throwable, tags)

    public fun <R> custom(
        level: Level,
        block: () -> R,
        throwable: Throwable?,
        tags: List<String>? = null
    ) = log(name, level, block, throwable, tags)

    public fun isOffEnabled(tags: List<String>?) = isEnabled(Level.off, tags)

    public fun isFatalEnabled(tags: List<String>?) = isEnabled(Level.fatal, tags)

    public fun isErrorEnabled(tags: List<String>?) = isEnabled(Level.error, tags)

    public fun isWarnEnabled(tags: List<String>?) = isEnabled(Level.warn, tags)

    public fun isInfoEnabled(tags: List<String>?) = isEnabled(Level.info, tags)

    public fun isDebugEnabled(tags: List<String>?) = isEnabled(Level.debug, tags)

    public fun isTraceEnabled(tags: List<String>?) = isEnabled(Level.trace, tags)

    public fun isAllEnabled(tags: List<String>?) = isEnabled(Level.all, tags)

    public fun isLevelEnabled(level: Level, tags: List<String>?) = isEnabled(level, tags)
}
