package org.la4k

import org.la4k.impl.getAvailableImplementations

public class Logger(val name: String) {

    private val loggers = implementations.map { it.getImplementationLogger(name) }

    public fun off(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.off, message, throwable, tags)

    public fun off(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.off, block, throwable, tags)

    public fun fatal(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.fatal, message, throwable, tags)

    public fun fatal(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.fatal, block, throwable, tags)

    public fun error(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.error, message, throwable, tags)

    public fun error(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.error, block, throwable, tags)

    public fun warn(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.warn, message, throwable, tags)

    public fun warn(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.warn, block, throwable, tags)

    public fun info(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.info, message, throwable, tags)

    public fun info(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.info, block, throwable, tags)

    public fun debug(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.debug, message, throwable, tags)

    public fun debug(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.debug, block, throwable, tags)

    public fun trace(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.trace, message, throwable, tags)

    public fun trace(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.trace, block, throwable, tags)

    public fun all(
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.all, message, throwable, tags)

    public fun all(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(Level.all, block, throwable, tags)

    public fun custom(
        level: Level,
        message: CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(level, message, throwable, tags)

    public fun custom(
        level: Level,
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tags: List<String>? = null
    ) = log(level, block, throwable, tags)

    public fun isOffEnabled(tags: List<String>?) = isEnabled(Level.off, tags)

    public fun isFatalEnabled(tags: List<String>?) = isEnabled(Level.fatal, tags)

    public fun isErrorEnabled(tags: List<String>?) = isEnabled(Level.error, tags)

    public fun isWarnEnabled(tags: List<String>?) = isEnabled(Level.warn, tags)

    public fun isInfoEnabled(tags: List<String>?) = isEnabled(Level.info, tags)

    public fun isDebugEnabled(tags: List<String>?) = isEnabled(Level.debug, tags)

    public fun isTraceEnabled(tags: List<String>?) = isEnabled(Level.trace, tags)

    public fun isAllEnabled(tags: List<String>?) = isEnabled(Level.all, tags)

    public fun isLevelEnabled(level: Level, tags: List<String>?) = isEnabled(level, tags)

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

    private companion object {

        private val implementations = getAvailableImplementations()
    }
}
