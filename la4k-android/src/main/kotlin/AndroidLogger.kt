/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.android

import org.la4k.Logger

import android.util.Log

public class AndroidLogger(name: String) : Logger(name) {

    val targetName =
        if (name.length <= 23)
            name
        else
            "${name.take(10)}...${name.takeLast(10)}"

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (throwable == null)
            Log.e(targetName, message.toString())
        else
            Log.e(targetName, message.toString(), throwable)
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (throwable == null)
            Log.e(targetName, message.toString())
        else
            Log.e(targetName, message.toString(), throwable)
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (throwable == null)
            Log.w(targetName, message.toString())
        else
            Log.w(targetName, message.toString(), throwable)
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ) {
        if (throwable == null)
            Log.i(targetName, message.toString())
        else
            Log.i(targetName, message.toString(), throwable)
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (throwable == null)
            Log.d(targetName, message.toString())
        else
            Log.d(targetName, message.toString(), throwable)
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit { }

    public override fun isFatalEnabled(tag: String?): Boolean =
        Log.isLoggable(targetName, Log.ERROR)

    public override fun isErrorEnabled(tag: String?): Boolean =
        Log.isLoggable(targetName, Log.ERROR)

    public override fun isWarnEnabled(tag: String?): Boolean =
        Log.isLoggable(targetName, Log.WARN)

    public override fun isInfoEnabled(tag: String?): Boolean =
        Log.isLoggable(targetName, Log.INFO)

    public override fun isDebugEnabled(tag: String?): Boolean =
        Log.isLoggable(targetName, Log.DEBUG)

    public override fun isTraceEnabled(tag: String?): Boolean =
        false
}
