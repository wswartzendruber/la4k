/*
 * Any copyright is dedicated to the Public Domain.
 *
 * Copyright 2021 William Swartzendruber
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
