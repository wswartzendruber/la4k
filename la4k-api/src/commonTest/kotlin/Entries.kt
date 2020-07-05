/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.test

import org.la4k.impl.Level

val entries = mutableListOf<Entry>()

data class Entry(
    val name: String,
    val level: Level,
    val message: CharSequence,
    val throwable: Throwable? = null,
    val tag: String? = null
)