package org.la4k.impl

internal expect fun getImplementations(): List<Implementation>

public abstract class Implementation {

    public abstract fun getImplementationLogger(name: String): ImplementationLogger
}
