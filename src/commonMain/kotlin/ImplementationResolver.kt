package org.la4k.api

internal expect class ImplementationResolver {

    companion object {

        public fun resolveAllImplementations() : List<ImplementationResolver>
    }
}
