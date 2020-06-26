/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.proxy

import org.la4k.impl.Level

/**
 * Invoked by this bridge for every logging event it handles.
 */
public var logEvent: (String, Level, CharSequence, Throwable?, String?) -> Unit =
    { _, _, _, _, _ -> }

/**
 * Invoked by this bridge to determine if a name, logging level, and tag combination should be
 * considered enabled or not.
 */
public var isLevelEnabled: (String, Level, String?) -> Boolean = { _, _, _ -> true }
