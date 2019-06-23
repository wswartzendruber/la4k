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

internal actual fun <R> log(
    name: String,
    level: Level,
    block: () -> R,
    throwable: Throwable?,
    tags: List<String>?
) {
    if (isEnabled(level, tags)) {
        // Do something.
    }
}

internal actual fun isEnabled(level: Level, tags: List<String>?) = true
