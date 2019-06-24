package org.la4k.api.impl

import org.la4k.api.Level

public abstract class ProviderLogger protected constructor(val name: String) {

    public abstract fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>?
    )

    public abstract fun isEnabled(level: Level, tags: List<String>?): Boolean
}
