package org.la4k.impl

public abstract class Logger protected constructor(val name: String) {

    public abstract fun log(
        level: Level,
        message: CharSequence,
        throwable: Throwable?,
        tag: String?
    )

    public abstract fun isEnabled(level: Level, tag: String?): Boolean
}
