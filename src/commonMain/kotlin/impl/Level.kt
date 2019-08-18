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
