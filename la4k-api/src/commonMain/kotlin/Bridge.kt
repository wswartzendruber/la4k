/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k

/**
 * Extended by LA4K bridges to connect calls from [logger] to internal, bridge-specific loggers.
 */
public abstract class Bridge {

    /**
     * Called by [org.la4k.logger] when necessary to create an internal logger for the bridge.
     *
     * @param[name] The same name that was passed to the [logger] function.
     */
    public abstract fun createLogger(name: String): Logger
}
