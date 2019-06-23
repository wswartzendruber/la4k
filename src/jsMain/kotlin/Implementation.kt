package org.la4k.api

public actual fun refresh() {
    // Do something.
}

internal actual fun log(
    name: String,
    level: Level,
    message: CharSequence,
    throwable: Throwable?,
    tags: List<String>?
) {
    // Do something.
}

internal actual fun isEnabled(level: Level, tags: List<String>?) = true
