/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

/**
 * Represents the six logging levels supported by LA4K.
 */
public enum class Level(public val rank: Int) {
    FATAL(1),
    ERROR(2),
    WARN(3),
    INFO(4),
    DEBUG(5),
    TRACE(6),
}
