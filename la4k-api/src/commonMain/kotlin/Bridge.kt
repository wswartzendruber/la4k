/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

internal expect val bridge: Bridge

/**
 * Extended by LA4K bridges to connect calls from [logger] to bridge-specific loggers.
 */
public abstract class Bridge {

    /**
     * Called by [org.la4k.logger] when necessary to create an internal logger for the bridge.
     *
     * @param[name] The name that was passed to the [logger] function.
     */
    public abstract fun createLogger(name: String): Logger
}
