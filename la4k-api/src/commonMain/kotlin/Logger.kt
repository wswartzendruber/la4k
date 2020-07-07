/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k

/**
 * The main class of the LA4K API; libraries should use it and only it for logging via LA4K.
 * Instances of this class can be retrieved by calling [logger].
 */
public abstract class Logger protected constructor(public val name: String) {

    /**
     * Selectively dispatches a message that an unrecoverable error has occurred.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public inline fun fatal(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit {
        if (isFatalEnabled(tag))
            fatal(block(), throwable, tag)
    }

    /**
     * Dispatches a [message] that an unrecoverable error has occurred.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun fatal(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit

    /**
     * Selectively dispatches a message that a recoverable error has occurred.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public inline fun error(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit {
        if (isErrorEnabled(tag))
            error(block(), throwable, tag)
    }

    /**
     * Dispatches a [message] that a recoverable error has occurred.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun error(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit

    /**
     * Selectively dispatches a message that a possible issue has arisen.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public inline fun warn(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit {
        if (isWarnEnabled(tag))
            warn(block(), throwable, tag)
    }

    /**
     * Dispatches a [message] that a possible issue has arisen.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun warn(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit

    /**
     * Selectively dispatches an arbitrary informational message.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public inline fun info(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit {
        if (isInfoEnabled(tag))
            info(block(), throwable, tag)
    }

    /**
     * Dispatches an arbitrary informational [message].
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun info(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit

    /**
     * Selectively dispatches a message containing diagnostics information.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public inline fun debug(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit {
        if (isDebugEnabled(tag))
            debug(block(), throwable, tag)
    }

    /**
     * Dispatches a [message] containing diagnostics information.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun debug(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit

    /**
     * Selectively dispatches a message containing internal state information.
     *
     * @param[block] The lambda to evaluate for a logging message.
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public inline fun trace(
        throwable: Throwable? = null,
        tag: String? = null,
        block: () -> Any?
    ): Unit {
        if (isTraceEnabled(tag))
            trace(block(), throwable, tag)
    }

    /**
     * Dispatches a [message] containing internal state information.
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun trace(
        message: Any?,
        throwable: Throwable? = null,
        tag: String? = null
    ): Unit

    /**
     * Checks if messages about unrecoverable errors will be logged.
     *
     * @param[tag] A tag qualifier.
     */
    public abstract fun isFatalEnabled(tag: String? = null): Boolean

    /**
     * Checks if messages about recoverable errors will be logged.
     *
     * @param[tag] A tag qualifier.
     */
    public abstract fun isErrorEnabled(tag: String? = null): Boolean

    /**
     * Checks if messages about possible issues will be logged.
     *
     * @param[tag] A tag qualifier.
     */
    public abstract fun isWarnEnabled(tag: String? = null): Boolean

    /**
     * Checks if arbitrary informational messages will be logged.
     *
     * @param[tag] A tag qualifier.
     */
    public abstract fun isInfoEnabled(tag: String? = null): Boolean

    /**
     * Checks if messages containing diagnostics information will be logged.
     *
     * @param[tag] A tag qualifier.
     */
    public abstract fun isDebugEnabled(tag: String? = null): Boolean

    /**
     * Checks if messages containing internal state information will be logged.
     *
     * @param[tag] A tag qualifier.
     */
    public abstract fun isTraceEnabled(tag: String? = null): Boolean
}
