/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k

import org.la4k.impl.BridgeLogger
import org.la4k.impl.Level

/**
 * The main class of the LA4K API; libraries should use it and only it for logging via LA4K.
 * Instances of this class can be retrieved by calling [logger].
 *
 * At any time, zero or more logging bridges are registered for use, and all instances share the
 * same registry. Any time a logging function is invoked by an instance, the registry is checked
 * to see if the bridge roster has changed. If it has, the instance updates its internal cache
 * and continues.
 *
 * Available bridges are discovered at some point before the first instance of this class is
 * made. Subsequent discoveries can be invoked manually via the companion's [refresh] function.
 */
public class Logger internal constructor(val name: String) {

    private val loggers = mutableListOf<BridgeLogger>()
    private var knownHashCode = 0

    /**
     * Dispatches a [message] that an unrecoverable error has occurred to all bridges.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun fatal(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit = log(Level.FATAL, message, throwable, tag)

    /**
     * Selectively dispatches a message that an unrecoverable error has occurred.
     *
     * Only bridges that report being enabled for the given level and [tag] combination will
     * receive the message. If no bridges report being enabled, then the [block] will not be
     * evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun fatal(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit = log(Level.FATAL, block, throwable, tag)

    /**
     * Dispatches a [message] that a recoverable error has occurred to all bridges.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun error(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit = log(Level.ERROR, message, throwable, tag)

    /**
     * Selectively dispatches a message that a recoverable error has occurred.
     *
     * Only bridges that report being enabled for the given level and [tag] combination will
     * receive the message. If no bridges report being enabled, then the [block] will not be
     * evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun error(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit = log(Level.ERROR, block, throwable, tag)

    /**
     * Dispatches a [message] that a possible issue has arisen to all bridges.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun warn(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit = log(Level.WARN, message, throwable, tag)

    /**
     * Selectively dispatches a message that a possible issue has arisen.
     *
     * Only bridges that report being enabled for the given level and [tag] combination will
     * receive the message. If no bridges report being enabled, then the [block] will not be
     * evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun warn(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit = log(Level.WARN, block, throwable, tag)

    /**
     * Dispatches an arbitrary informational [message] to all bridges.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun info(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit = log(Level.INFO, message, throwable, tag)

    /**
     * Selectively dispatches an arbitrary informational message.
     *
     * Only bridges that report being enabled for the given level and [tag] combination will
     * receive the message. If no bridges report being enabled, then the [block] will not be
     * evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun info(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit = log(Level.INFO, block, throwable, tag)

    /**
     * Dispatches a [message] containing diagnostics information to all bridges.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun debug(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit = log(Level.DEBUG, message, throwable, tag)

    /**
     * Selectively dispatches a message containing diagnostics information.
     *
     * Only bridges that report being enabled for the given level and [tag] combination will
     * receive the message. If no bridges report being enabled, then the [block] will not be
     * evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun debug(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit = log(Level.DEBUG, block, throwable, tag)

    /**
     * Dispatches a [message] containing internal state information to all bridges.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun trace(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit = log(Level.TRACE, message, throwable, tag)

    /**
     * Selectively dispatches a message containing internal state information.
     *
     * Only bridges that report being enabled for the given level and [tag] combination will
     * receive the message. If no bridges report being enabled, then the [block] will not be
     * evaluated at all.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public fun trace(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit = log(Level.TRACE, block, throwable, tag)

    /**
     * Checks if any bridge is enabled to show messages about unrecoverable errors.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isFatalEnabled(tag: String? = null): Boolean = isEnabled(Level.FATAL, tag)

    /**
     * Checks if any bridge is enabled to show messages about recoverable errors.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isErrorEnabled(tag: String? = null): Boolean = isEnabled(Level.ERROR, tag)

    /**
     * Checks if any bridge is enabled to show messages about possible issues.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isWarnEnabled(tag: String? = null): Boolean = isEnabled(Level.WARN, tag)

    /**
     * Checks if any bridge is enabled to show arbitrary informational messages.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isInfoEnabled(tag: String? = null): Boolean = isEnabled(Level.INFO, tag)

    /**
     * Checks if any bridge is enabled to show messages containing diagnostics
     * information.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isDebugEnabled(tag: String? = null): Boolean = isEnabled(Level.DEBUG, tag)

    /**
     * Checks if any bridge is enabled to show messages containing internal state
     * information.
     *
     * @param[tag] A tag qualifier.
     */
    public fun isTraceEnabled(tag: String? = null): Boolean = isEnabled(Level.TRACE, tag)

    private fun log(
        level: Level,
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ) {
        validateLoggers()
        for (logger in loggers)
            logger.log(level, message, throwable, tag)
    }

    private fun log(
        level: Level,
        block: () -> Any?,
        throwable: Throwable?,
        tag: String?
    ) {
        validateLoggers()

        var value: Any? = null

        for (logger in loggers) {
            if (logger.isEnabled(level, tag)) {
                if (value == null)
                    value = block()
                logger.log(level, value, throwable, tag)
            }
        }
    }

    private fun isEnabled(level: Level, tag: String?): Boolean {

        validateLoggers()

        return loggers.any { it.isEnabled(level, tag) }
    }

    private fun validateLoggers() {
        if (knownHashCode != currentHashCode) {
            platformSynchronized(bridges) {
                loggers.clear()
                loggers.addAll(bridges.map({ it.getBridgeLogger(name) }))
                knownHashCode = currentHashCode
            }
        }
    }

    private companion object {

        init {
            refresh()
        }
    }
}
