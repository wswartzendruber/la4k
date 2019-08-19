/*
 * Copyright 2019 William Swartzendruber
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
 */

package org.la4k.impl

internal expect fun getImplementations(): List<Implementation>

/**
 * Extended by LA4K implementation bridges to connect the [org.la4k.Logger] class to actual
 * logging implementations.
 */
public abstract class Implementation {

    /**
     * Called by each instance of the [org.la4k.Logger] class to get an internal logger
     * specific to that instance and for the extending implementation.
     *
     * @param[name] The same name that was passed to the calling [org.la4k.Logger] class on
     *     creation.
     */
    public abstract fun getImplementationLogger(name: String): ImplementationLogger
}
