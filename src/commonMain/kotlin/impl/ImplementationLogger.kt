package org.la4k.impl

import org.la4k.Level

public abstract class ImplementationLogger protected constructor(val name: String) {

    public abstract fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tags: List<String>?
    )

    public abstract fun isEnabled(level: Level, tags: List<String>?): Boolean
}
