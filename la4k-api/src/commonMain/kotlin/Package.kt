/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k

internal val bridge = getBridge() ?: NullBridge()

internal val loggers = mutableMapOf<String, Logger>()

/**
 * Returns a [Logger] instance with the specified name. If an instance with that name does not
 * exist, then it will be created and stored for future use.
 */
public fun logger(name: String): Logger =
    platformSynchronized(loggers) {
        loggers.getOrPut(name, { bridge.createLogger(name) })
    }
