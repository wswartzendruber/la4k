/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k

import org.la4k.impl.Bridge

import java.util.ServiceLoader

internal actual fun getBridges() =
    ServiceLoader
        .load(Bridge::class.java)
        .apply { reload() }
        .asSequence()
        .toList()

internal actual fun <R> platformSynchronized(lock: Any, block: () -> R) =
    synchronized(lock, block)
