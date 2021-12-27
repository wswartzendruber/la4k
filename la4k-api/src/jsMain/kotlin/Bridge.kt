/*
 * Copyright 2020 William Swartzendruber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.la4k

private var activeBridge: Bridge? = null

internal actual val bridge by lazy {

    if (activeBridge == null)
        activeBridge = NullBridge()

    activeBridge!!
}

/**
 * Called by applications to activate a bridge. This function can only be called once and must
 * be called before [logger] is called. Otherwise, the [NullBridge] will be used for the
 * duration of the application.
 */
public fun activateBridge(value: Bridge): Unit {
    if (activeBridge == null)
        activeBridge = value
    else
        throw BridgeActivationException("A bridge has already been activated.")
}
