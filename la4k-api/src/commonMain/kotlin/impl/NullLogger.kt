/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.impl

import org.la4k.Logger

public class NullLogger(name: String) : Logger(name) {

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun isFatalEnabled(tag: String?): Boolean = false

    public override fun isErrorEnabled(tag: String?): Boolean = false

    public override fun isWarnEnabled(tag: String?): Boolean = false

    public override fun isInfoEnabled(tag: String?): Boolean = false

    public override fun isDebugEnabled(tag: String?): Boolean = false

    public override fun isTraceEnabled(tag: String?): Boolean = false
}
