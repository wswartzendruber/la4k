/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

internal class NullLogger(name: String) : Logger(name) {

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit { }

    public override fun isFatalEnabled(tag: String?): Boolean = false

    public override fun isErrorEnabled(tag: String?): Boolean = false

    public override fun isWarnEnabled(tag: String?): Boolean = false

    public override fun isInfoEnabled(tag: String?): Boolean = false

    public override fun isDebugEnabled(tag: String?): Boolean = false

    public override fun isTraceEnabled(tag: String?): Boolean = false
}
