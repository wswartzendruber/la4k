/*
 * Copyright 2020 William Swartzendruber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
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
