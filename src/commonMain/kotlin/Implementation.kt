package org.la4k.api

public expect fun refresh()

internal expect fun log(
    name: String,
    level: Level,
    message: CharSequence,
    throwable: Throwable?,
    tags: List<String>?
)

internal expect fun <R> log(
    name: String,
    level: Level,
    block: () -> R,
    throwable: Throwable?,
    tags: List<String>?
)

internal expect fun isEnabled(level: Level, tags: List<String>?): Boolean
