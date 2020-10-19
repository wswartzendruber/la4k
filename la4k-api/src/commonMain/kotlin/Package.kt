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

package org.la4k

internal expect fun <R> platformSynchronized(lock: Any, block: () -> R): R

internal expect val bridge: Bridge

private val loggers = mutableMapOf<String, Logger>()

/**
 * Returns a [Logger] instance with the specified name. If an instance with that name does not
 * exist, then it will be created and stored for future use.
 */
public fun logger(name: String): Logger =
    platformSynchronized(loggers) {
        loggers.getOrPut(name, { bridge.createLogger(name) })
    }
