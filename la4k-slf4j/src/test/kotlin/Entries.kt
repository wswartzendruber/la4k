/*
 * Copyright 2021 William Swartzendruber
 *
 * To the extent possible under law, the person who associated CC0 with this file has waived all
 * copyright and related or neighboring rights to this file.
 *
 * You should have received a copy of the CC0 legalcode along with this work. If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 *
 * SPDX-License-Identifier: CC0-1.0
 */

package org.la4k.slf4j.test

val entries = mutableListOf<Entry>()

data class Entry(
    val name: String,
    val level: String,
    val message: String,
    val throwable: Throwable? = null,
    val tag: String? = null,
)
