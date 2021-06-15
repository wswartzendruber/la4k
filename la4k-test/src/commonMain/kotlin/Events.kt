/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.test

import org.la4k.Level

internal val events = mutableListOf<Event>()

/**
 * Represents a single test event that has been logged.
 *
 * @param[name] The name of the logger that dispatched the event.
 * @param[level] The level with which the event was dispatched.
 * @param[message] The logging message of the event.
 * @param[throwable] An exception relating to the cause of the event.
 * @param[tag] An arbitrary tag that was applied to the event.
 */
public data class Event(
    val name: String,
    val level: Level,
    val message: Any?,
    val throwable: Throwable?,
    val tag: String?,
)

/**
 * Clears the store of all test events.
 */
public fun clear(): Unit = platformSynchronized(events) {
    events.clear()
}

/**
 * Returns the number of test events that match the given [predicate].
 */
public fun count(predicate: (Event) -> Boolean): Int = platformSynchronized(events) {
    events.map({ predicate(it) }).count({ it == true })
}
