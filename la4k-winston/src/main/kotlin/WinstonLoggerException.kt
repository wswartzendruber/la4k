/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k.winston

/**
 * Indicates a problem with binding a LA4K logger to a Winston logger.
 */
public open class WinstonLoggerException : Exception {
    public constructor() : super()
    public constructor(message: String?) : super(message)
    public constructor(cause: Throwable?) : super(cause)
    public constructor(message: String?, cause: Throwable?) : super(message,cause)
}
