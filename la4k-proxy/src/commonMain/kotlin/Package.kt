/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.proxy

import org.la4k.impl.Level

public var logMessage: (String, Level, CharSequence, Throwable?, String?) -> Unit =
    { _, _, _, _, _ -> }

public var isLevelEnabled: (Level, String?) -> Boolean = { _, _ -> true }
