/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.android

import org.la4k.impl.BridgeLogger
import org.la4k.impl.Level

import android.util.Log as TargetLog

public class AndroidBridgeLogger(name: String) : BridgeLogger(name) {

    val targetName =
        if (name.length <= 23)
            name
        else
            "${name.take(10)}...${name.takeLast(10)}"

    /**
     * Routes an event from an `org.la4k.Logger` instance to `android.util.Log`.
     *
     * The name of the `org.la4k.Logger` instance is used as the tag for `android.util.Log`,
     * unless that name exceeds 23 characters. In this case, the first and last ten characters
     * of the name will be joined together by three periods. So `org.myproject.MyExampleClass`
     * would become `org.myproj...ampleClass`.
     *
     * The [level] is mapped in the following manner:
     *
     * * FATAL -> ERROR
     * * ERROR -> ERROR
     * * WARN -> ERROR
     * * INFO -> INFO
     * * DEBUG -> DEBUG
     * * TRACE -> (dropped)
     *
     * The [message] is always converted to a string via its `toString()` method.
     *
     * The [throwable] is passed if it is not `null`.
     *
     * The [tag] is always discarded.
     */
    public override fun log(
        level: Level,
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (throwable == null) {
            when (level) {
                Level.FATAL -> TargetLog.e(targetName, message.toString())
                Level.ERROR -> TargetLog.e(targetName, message.toString())
                Level.WARN -> TargetLog.e(targetName, message.toString())
                Level.INFO -> TargetLog.i(targetName, message.toString())
                Level.DEBUG -> TargetLog.d(targetName, message.toString())
                Level.TRACE -> { }
            }
        } else {
            when (level) {
                Level.FATAL -> TargetLog.e(targetName, message.toString(), throwable)
                Level.ERROR -> TargetLog.e(targetName, message.toString(), throwable)
                Level.WARN -> TargetLog.e(targetName, message.toString(), throwable)
                Level.INFO -> TargetLog.i(targetName, message.toString(), throwable)
                Level.DEBUG -> TargetLog.d(targetName, message.toString(), throwable)
                Level.TRACE -> { }
            }
        }
    }

    /**
     * Queries `android.util.Log` to determine if a logging level is enabled for it and returns
     * it.
     *
     * The name of the `org.la4k.Logger` instance is used as the tag for `android.util.Log`,
     * unless that name exceeds 23 characters. In this case, the first and last ten characters
     * of the name will be joined together by three periods. So `org.myproject.MyExampleClass`
     * would become `org.myproj...ampleClass`.
     *
     * The [level] is mapped to a `java.util.logging.Level` in the following manner:
     *
     * * FATAL -> ERROR
     * * ERROR -> ERROR
     * * WARN -> ERROR
     * * INFO -> INFO
     * * DEBUG -> DEBUG
     * * TRACE -> (dropped)
     *
     * The [tag] is always discarded.
     */
    public override fun isEnabled(level: Level, tag: String?): Boolean =
        when (level) {
            Level.FATAL -> TargetLog.isLoggable(targetName, TargetLog.ERROR)
            Level.ERROR -> TargetLog.isLoggable(targetName, TargetLog.ERROR)
            Level.WARN -> TargetLog.isLoggable(targetName, TargetLog.WARN)
            Level.INFO -> TargetLog.isLoggable(targetName, TargetLog.INFO)
            Level.DEBUG -> TargetLog.isLoggable(targetName, TargetLog.DEBUG)
            Level.TRACE -> false
        }
}
