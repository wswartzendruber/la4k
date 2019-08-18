package org.la4k

import org.la4k.impl.getImplementations
import org.la4k.impl.Implementation
import org.la4k.impl.ImplementationLogger
import org.la4k.impl.Level

/**
 * The main class of the LA4K API; libraries should use it and only it for logging via LA4K.
 *
 * At any time, zero or more logging implementations are registered for use, and all instances
 * share the same registry. Any time a logging function is invoked by an instance, the registry
 * is checked to see if the implementation roster has changed. If it has, the instance updates
 * its internal cache and continues.
 *
 * The presence of an implementation is defined by the presence of its LA4K-specific bridge.
 * Available implementations are discovered at some point before the first instance of this
 * class is made. Subsequent discoveries can be invoked manually via the companion's [refresh]
 * function.
 *
 * @property[name] The name of the logger.
 *
 * @constructor Initializes a new logger with the provided [name].
 */
public class Logger(val name: String) {

    private val loggers = mutableListOf<ImplementationLogger>()
    private var knownHashCode = 0

    /**
     * Dispatches a message that an unrecoverable error has occurred to all implementations.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun fatal(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.FATAL, message, throwable, tag)

    /**
     * Selectively dispatches a message that an unrecoverable error has occurred.
     *
     * Only implementations that report being enabled for the given level and [tag] combination
     * will receive the message. If no implementations report being enabled, then the [block]
     * will not be evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun fatal(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.FATAL, block, throwable, tag)

    /**
     * Dispatches a message that a recoverable error has occurred to all implementations.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun error(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.ERROR, message, throwable, tag)

    /**
     * Selectively dispatches a message that a recoverable error has occurred.
     *
     * Only implementations that report being enabled for the given level and [tag] combination
     * will receive the message. If no implementations report being enabled, then the [block]
     * will not be evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun error(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.ERROR, block, throwable, tag)

    /**
     * Dispatches a message that a possible issue has arisen to all implementations.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun warn(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.WARN, message, throwable, tag)

    /**
     * Selectively dispatches a message that a possible issue has arisen.
     *
     * Only implementations that report being enabled for the given level and [tag] combination
     * will receive the message. If no implementations report being enabled, then the [block]
     * will not be evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun warn(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.WARN, block, throwable, tag)

    /**
     * Dispatches an arbitrary informational message to all implementations.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun info(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.INFO, message, throwable, tag)

    /**
     * Selectively dispatches an arbitrary informational message.
     *
     * Only implementations that report being enabled for the given level and [tag] combination
     * will receive the message. If no implementations report being enabled, then the [block]
     * will not be evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun info(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.INFO, block, throwable, tag)

    /**
     * Dispatches a message containing diagnostics information to all implementations.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun debug(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.DEBUG, message, throwable, tag)

    /**
     * Selectively dispatches a message containing diagnostics information.
     *
     * Only implementations that report being enabled for the given level and [tag] combination
     * will receive the message. If no implementations report being enabled, then the [block]
     * will not be evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun debug(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.DEBUG, block, throwable, tag)

    /**
     * Dispatches a message containing internal state information to all implementations.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun trace(
        message: CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.TRACE, message, throwable, tag)

    /**
     * Selectively dispatches a message containing internal state information.
     *
     * Only implementations that report being enabled for the given level and [tag] combination
     * will receive the message. If no implementations report being enabled, then the [block]
     * will not be evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun trace(
        block: () -> CharSequence,
        throwable: Throwable? = null,
        tag: String? = null
    ) = log(Level.TRACE, block, throwable, tag)

    /**
     * Checks if any implementation is enabled to show messages about unrecoverable errors.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isFatalEnabled(tag: String?) = isEnabled(Level.FATAL, tag)

    /**
     * Checks if any implementation is enabled to show messages about recoverable errors.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isErrorEnabled(tag: String?) = isEnabled(Level.ERROR, tag)

    /**
     * Checks if any implementation is enabled to show messages about possible issues.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isWarnEnabled(tag: String?) = isEnabled(Level.WARN, tag)

    /**
     * Checks if any implementation is enabled to show arbitrary informational messages.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isInfoEnabled(tag: String?) = isEnabled(Level.INFO, tag)

    /**
     * Checks if any implementation is enabled to show messages containing diagnostics
     * information.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isDebugEnabled(tag: String?) = isEnabled(Level.DEBUG, tag)

    /**
     * Checks if any implementation is enabled to show messages containing internal state
     * information.
     *
     * @param[tag] A tag qualifier.
     */
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
                loggers.addAll(implementations.map({ it.getImplementationLogger(name) }))
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

        /**
         * Forces a reinventory of all available implementations.
         *
         * This should only be done by host applications if a new logging implementation has
         * been made available since application startup. It may cause all instances of this
         * class to have to separately reinstanciate internal handles to all available
         * implementations, which will happen on each instance's next logging call.
         */
        public fun refresh() {
            platformSynchronized(implementations) {
                implementations.clear()
                implementations.addAll(getImplementations())
                currentHashCode = implementations.hashCode()
            }
        }
    }
}
