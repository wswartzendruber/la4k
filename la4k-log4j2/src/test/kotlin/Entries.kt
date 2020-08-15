/*
 * Any copyright is dedicated to the Public Domain.
 * https://creativecommons.org/publicdomain/zero/1.0/
 */

package org.la4k.log4j2.test

import org.apache.logging.log4j.Level

val entries = mutableListOf<Entry>()

data class Entry(
    val name: String,
    val level: Level,
    val message: String,
    val throwable: Throwable? = null,
    val tag: String? = null,
)
