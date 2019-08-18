package org.la4k.impl

public abstract class ImplementationLogger protected constructor(val name: String) {

    public abstract fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    )

    public abstract fun isEnabled(level: Level, tag: String?): Boolean
}
