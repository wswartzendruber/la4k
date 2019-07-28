package org.la4k

import org.la4k.impl.currentImplementations
import org.la4k.impl.Implementation
import org.la4k.impl.Level
import org.la4k.impl.Logger as ImplementationLogger

public class Logger(val name: String) {

    private val loggers = mutableListOf<ImplementationLogger>()
    private var knownHashCode = 0

    public fun fatal(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.FATAL, message, throwable, tag)

    public fun fatal(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.FATAL, block, throwable, tag)

    public fun error(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.ERROR, message, throwable, tag)

    public fun error(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.ERROR, block, throwable, tag)

    public fun warn(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.WARN, message, throwable, tag)

    public fun warn(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.WARN, block, throwable, tag)

    public fun info(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.INFO, message, throwable, tag)

    public fun info(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.INFO, block, throwable, tag)

    public fun debug(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.DEBUG, message, throwable, tag)

    public fun debug(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.DEBUG, block, throwable, tag)

    public fun trace(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.TRACE, message, throwable, tag)

    public fun trace(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.TRACE, block, throwable, tag)

    public fun isFatalEnabled(tag: String?) = isEnabled(Level.FATAL, tag)

    public fun isErrorEnabled(tag: String?) = isEnabled(Level.ERROR, tag)

    public fun isWarnEnabled(tag: String?) = isEnabled(Level.WARN, tag)

    public fun isInfoEnabled(tag: String?) = isEnabled(Level.INFO, tag)

    public fun isDebugEnabled(tag: String?) = isEnabled(Level.DEBUG, tag)

    public fun isTraceEnabled(tag: String?) = isEnabled(Level.TRACE, tag)

    private fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ) {
        validateLoggers()
        for (logger in loggers)
            logger.log(level, message, throwable, tag)
    }

    private fun log(
        level: Level,
        block: () -> CharSequence,
        throwable: Throwable?,
        tag: String?
    ) {
        validateLoggers()

        var value: CharSequence? = null

        for (logger in loggers) {
            if (logger.isEnabled(level, tag)) {
                if (value == null)
                    value = block()
                logger.log(level, value, throwable, tag)
            }
        }
    }

    private fun isEnabled(level: Level, tag: String?) {
        validateLoggers()
        loggers.any { it.isEnabled(level, tag) }
    }

    private fun validateLoggers() {
        if (knownHashCode != currentHashCode) {
            platformSynchronized(implementations) {
                loggers.clear()
                loggers.addAll(implementations.map({ it.getLogger(name) }))
                knownHashCode = currentHashCode
            }
        }
    }

    public companion object {

        private val implementations = mutableListOf<Implementation>()
        private var currentHashCode = 0

        init {
            refresh()
        }

        public fun refresh() {
            platformSynchronized(implementations) {
                implementations.clear()
                implementations.addAll(currentImplementations())
                currentHashCode = implementations.hashCode()
            }
        }
    }
}
