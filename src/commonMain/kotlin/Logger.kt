package org.la4k

import org.la4k.impl.implementations
import org.la4k.impl.Level

public class Logger(val name: String) {

    private val loggers = implementations().map { it.getLogger(name) }

    public fun fatal(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.FATAL, message, throwable, tags)

    public fun fatal(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.FATAL, block, throwable, tags)

    public fun error(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.ERROR, message, throwable, tags)

    public fun error(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.ERROR, block, throwable, tags)

    public fun warn(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.WARN, message, throwable, tags)

    public fun warn(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.WARN, block, throwable, tags)

    public fun info(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.INFO, message, throwable, tags)

    public fun info(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.INFO, block, throwable, tags)

    public fun debug(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.DEBUG, message, throwable, tags)

    public fun debug(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.DEBUG, block, throwable, tags)

    public fun trace(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.TRACE, message, throwable, tags)

    public fun trace(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.TRACE, block, throwable, tags)

    public fun isFatalEnabled(tags: List<String>?) = isEnabled(Level.FATAL, tags)

    public fun isErrorEnabled(tags: List<String>?) = isEnabled(Level.ERROR, tags)

    public fun isWarnEnabled(tags: List<String>?) = isEnabled(Level.WARN, tags)

    public fun isInfoEnabled(tags: List<String>?) = isEnabled(Level.INFO, tags)

    public fun isDebugEnabled(tags: List<String>?) = isEnabled(Level.DEBUG, tags)

    public fun isTraceEnabled(tags: List<String>?) = isEnabled(Level.TRACE, tags)

    private fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>?
    ) {
        for (logger in loggers)
            logger.log(level, message, throwable, tags)
    }

    private fun log(
        level: Level,
        block: () -> CharSequence,
        throwable: Throwable?,
        tags: List<String>?
    ) {

        var value: CharSequence? = null

        for (logger in loggers) {
            if (logger.isEnabled(level, tags)) {
                if (value == null)
                    value = block()
                logger.log(level, value, throwable, tags)
            }
        }
    }

    private fun isEnabled(level: Level, tags: List<String>?) =
        loggers.any { it.isEnabled(level, tags) }
}
