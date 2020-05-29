/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

package org.la4k.slf4j

import org.la4k.impl.Implementation
import org.la4k.impl.ImplementationLogger

public class Slf4jImplementation() : Implementation() {

    public override fun getImplementationLogger(name: String): ImplementationLogger =
        Slf4jImplementationLogger(name)
}
