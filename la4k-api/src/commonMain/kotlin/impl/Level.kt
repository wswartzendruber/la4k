/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
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
