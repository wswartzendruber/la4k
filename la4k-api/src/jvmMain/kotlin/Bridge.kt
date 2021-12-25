/*
 * SPDX-FileCopyrightText: 2020 William Swartzendruber <wswartzendruber@gmail.com>
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

import java.util.ServiceLoader

internal actual val bridge = ServiceLoader.load(Bridge::class.java).lastOrNull() ?: NullBridge()
