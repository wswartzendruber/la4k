package org.la4k.impl

internal expect fun getAvailableImplementations(): List<Implementation>

public abstract class Implementation {

    public abstract fun getImplementationLogger(name: String): ImplementationLogger
}
