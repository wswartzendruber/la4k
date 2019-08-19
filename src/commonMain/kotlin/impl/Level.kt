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

/**
 * Defines the logging levels supported by LA4K.
 */
public enum class Level {

    /** Denotes that an unrecoverable error has occurred. */
    FATAL,

    /** Denotes that a recoverable error has occurred. */
    ERROR,

    /** Denotes that a possible issue has arisen. */
    WARN,

    /** Denotes arbitrary information. */
    INFO,

    /** Denotes diagnostics information. */
    DEBUG,

    /** Denotes internal state information. */
    TRACE
}
