/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k

import org.la4k.impl.Implementation

internal val implementations = mutableListOf<Implementation>()
internal var currentHashCode = 0

/**
 * Forces a reinventory of all available implementations.
 *
 * This should only be done by host applications if a new logging implementation has been made
 * available since application startup. It may cause all instances of this class to have to
 * separately reinstanciate internal handles to all available implementations, which will happen
 * on each instance's next logging call.
 */
public fun refresh(): Unit {
    platformSynchronized(implementations) {
        implementations.clear()
        implementations.addAll(getImplementations())
        currentHashCode = implementations.hashCode()
    }
}
