/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.proxy

import org.la4k.impl.Level
import org.la4k.impl.ImplementationLogger

public class ProxyImplementationLogger(name: String) : ImplementationLogger(name) {

    public override fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    ): Unit {
        if (isEnabled(level, tag)) {
            logMessage(name, level, message, throwable, tag)
        }
    }

    public override fun isEnabled(level: Level, tag: String?): Boolean =
        isLevelEnabled(level, tag)
}
