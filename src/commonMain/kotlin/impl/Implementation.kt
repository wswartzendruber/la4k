package org.la4k.impl

internal expect fun getImplementations(): List<Implementation>

/**
 * Extended by LA4K implementation bridges to connect the [org.la4k.Logger] class to actual
 * logging implementations.
 */
public abstract class Implementation {

    /**
     * Called by each instance of the [org.la4k.Logger] class to get an internal logger
     * specific to that instance and for the extending implementation.
     *
     * @param[name] The same name that was passed to the calling [org.la4k.Logger] class on
     *     creation.
     */
    public abstract fun getImplementationLogger(name: String): ImplementationLogger
}
