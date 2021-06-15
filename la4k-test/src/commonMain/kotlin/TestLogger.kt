/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.test

import org.la4k.Level
import org.la4k.Logger

public class TestLogger internal constructor(name: String) : Logger(name) {

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit = platformSynchronized(events) {
        events.add(Event(name, Level.FATAL, message, throwable, tag))
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit = platformSynchronized(events) {
        events.add(Event(name, Level.ERROR, message, throwable, tag))
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit = platformSynchronized(events) {
        events.add(Event(name, Level.WARN, message, throwable, tag))
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit = platformSynchronized(events) {
        events.add(Event(name, Level.INFO, message, throwable, tag))
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit = platformSynchronized(events) {
        events.add(Event(name, Level.DEBUG, message, throwable, tag))
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit = platformSynchronized(events) {
        events.add(Event(name, Level.TRACE, message, throwable, tag))
    }

    public override fun isFatalEnabled(tag: String?): Boolean = true

    public override fun isErrorEnabled(tag: String?): Boolean = true

    public override fun isWarnEnabled(tag: String?): Boolean = true

    public override fun isInfoEnabled(tag: String?): Boolean = true

    public override fun isDebugEnabled(tag: String?): Boolean = true

    public override fun isTraceEnabled(tag: String?): Boolean = true
}
