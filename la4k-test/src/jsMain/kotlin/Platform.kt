/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.test

internal actual fun <R> platformSynchronized(lock: Any, block: () -> R) =
    synchronized(lock, block)
