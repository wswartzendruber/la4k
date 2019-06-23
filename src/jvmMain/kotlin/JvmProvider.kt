package org.la4k.api

public interface JvmProvider {

    public fun log(
        name: String,
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>?
    )

    public fun isEnabled(level: Level, tags: List<String>?): Boolean
}
