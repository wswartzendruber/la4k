/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

import org.la4k.impl.Level
import org.la4k.impl.BridgeLogger

class TestBridgeLogger(name: String) : BridgeLogger(name) {

    override fun log(
        level: Level,
        message: Any?,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (isEnabled(level, tag)) {
            logEvent(name, level, message, throwable, tag)
        }
    }

    override fun isEnabled(level: Level, tag: String?): Boolean =
        isLevelEnabled(name, level, tag)
}
