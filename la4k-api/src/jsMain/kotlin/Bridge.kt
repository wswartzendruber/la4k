/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

private var activeBridge: Bridge? = null

internal actual val bridge by lazy {

    if (activeBridge == null)
        activeBridge = NullBridge()

    activeBridge!!
}

/**
 * Called by applications to activate a bridge. This function can only be called once and must
 * be called before [logger] is called. Otherwise, the [NullBridge] will be used for the
 * duration of the application.
 */
public fun activateBridge(value: Bridge): Unit {
    if (activeBridge == null)
        activeBridge = value
    else
        throw BridgeActivationException("A bridge has already been activated.")
}
