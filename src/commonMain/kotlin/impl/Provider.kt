package org.la4k.api.impl

internal expect fun getAvailableProviders(): List<Provider>

public abstract class Provider {

    public abstract fun getProviderLogger(name: String): ProviderLogger
}
