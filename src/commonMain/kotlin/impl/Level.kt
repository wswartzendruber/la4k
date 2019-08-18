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
 * Defines the logging levels supported by LA4K.
 */
public enum class Level {

    /** Denotes that an unrecoverable error has occurred. */
    FATAL,

    /** Denotes that a recoverable error has occurred. */
    ERROR,

    /** Denotes that a possible issue has arisen. */
    WARN,

    /** Denotes arbitrary information. */
    INFO,

    /** Denotes diagnostics information. */
    DEBUG,

    /** Denotes internal state information. */
    TRACE
}
