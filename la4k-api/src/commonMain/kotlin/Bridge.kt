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

internal expect val bridge: Bridge

/**
 * Extended by LA4K bridges to connect calls from [logger] to bridge-specific loggers.
 */
public abstract class Bridge {

    /**
     * Called by [org.la4k.logger] when necessary to create an internal logger for the bridge.
     *
     * @param[name] The name that was passed to the [logger] function.
     */
    public abstract fun createLogger(name: String): Logger
}
