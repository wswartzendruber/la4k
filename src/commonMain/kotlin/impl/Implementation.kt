/*
 * LA4K - Logging API for Kotlin
 * Copyright (C) 2019 William Swartzendruber
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program. If not, see <https://www.gnu.org/licenses/>.
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
