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

    public override fun log(
        level: Level,
        message: CharSequence,
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
