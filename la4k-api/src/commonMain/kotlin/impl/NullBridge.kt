/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.impl

import org.la4k.Logger

/**
 * Used when no other bridge has been configured for use.
 */
public class NullBridge : Bridge() {

    public override fun getLogger(name: String): Logger = NullLogger(name)
}
