/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.android

import android.util.Log

import org.la4k.Logger

public class AndroidLogger internal constructor(name: String) : Logger(name) {

    val targetName =
        if (name.length <= 23)
            name
        else
            "${name.take(10)}...${name.takeLast(10)}"

    public override fun fatal(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            Log.e(targetName, message.toString())
        else
            Log.e(targetName, message.toString(), throwable)
    }

    public override fun error(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            Log.e(targetName, message.toString())
        else
            Log.e(targetName, message.toString(), throwable)
    }

    public override fun warn(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            Log.w(targetName, message.toString())
        else
            Log.w(targetName, message.toString(), throwable)
    }

    public override fun info(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ) {
        if (throwable == null)
            Log.i(targetName, message.toString())
        else
            Log.i(targetName, message.toString(), throwable)
    }

    public override fun debug(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
    ): Unit {
        if (throwable == null)
            Log.d(targetName, message.toString())
        else
            Log.d(targetName, message.toString(), throwable)
    }

    public override fun trace(
        message: Any?,
        throwable: Throwable?,
        tag: String?,
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
