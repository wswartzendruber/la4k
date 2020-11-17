/*
 * Copyright 2020 William Swartzendruber
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package org.la4k

private var activeBridge: Bridge? = null

internal val bridge by lazy {
    if (activeBridge == null)
        activeBridge = NullBridge()
    activeBridge!!
}

public fun activateBridge(value: Bridge): Unit {
    if (activeBridge == null)
        activeBridge = value
    else
        throw BridgeActivationException()
}

public class BridgeActivationException : Exception("A bridge has already been activated.")
