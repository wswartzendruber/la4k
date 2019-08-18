/*
 * LA4K - Logging API for Kotlin
 * Copyright (C) 2019 William Swartzendruber
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.la4k.impl

/**
 * Extended by LA4K implementation bridges to provide logging functionality to specific
 * instances of the [org.la4k.Logger] class.
 */
public abstract class ImplementationLogger protected constructor(val name: String) {

    /**
     * Called by instances of [org.la4k.Logger] to forward a [message] to the implementation on
     * on their behalf at the specified [level].
     *
     * @param[throwable] An exception relating to the cause of the incident.
     * @param[tag] An arbitrary tag to apply to the message.
     */
    public abstract fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    )

    /**
     * Called by instances of [org.la4k.Logger] to determine if the implementation will show
     * a logging message from that instance at the specified [level].
     *
     * @param[tag] An arbitrary tag to check for.
     */
    public abstract fun isEnabled(level: Level, tag: String?): Boolean
}
