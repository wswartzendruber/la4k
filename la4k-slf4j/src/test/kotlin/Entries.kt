/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.slf4j.test

val entries = mutableListOf<Entry>()

data class Entry(
    val name: String,
    val level: String,
    val message: String,
    val throwable: Throwable? = null,
    val tag: String? = null
)