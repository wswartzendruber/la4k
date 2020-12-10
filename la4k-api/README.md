# Module la4k-api

`la4k-api` is used by libraries to log events in an entirely agnostic manner. In this way, the
library should not have to take any specific logging implementation into consideration.

The single `org.la4k` package in this module contains all necessary functionality.

## Package org.la4k

### Initialization

To use this library, import the `org.la4k.logger` function:

```kotlin
import org.la4k.logger
```

There are two ways to instansiate a logger. JVM and Android developers will likely prefer the
simplest approach:

```kotlin
val log = logger()
```

This will return an instance of `org.la4k.Logger` with the fully qualified name of the calling
class. Note that this approach **does not** depend on Kotlin/JVM reflection.

If anything other than the JVM or Android targets are being used, or if a component is a part of
the common source set, the name of the logger must be passed in as the only parameter:

```kotlin
val log = logger(this::class.qualifiedName)
```

As Kotlin/JS gains better reflection support, the need to pass the name in explicitly may be
removed.

### Usage

There are six logging levels: **FATAL**, **ERROR**, **WARN**, **INFO**, **DEBUG**, and
**TRACE**. Each logging statement must include a message and may optionally include an exception
and/or a tag of some kind:

```kotlin
log.fatal("This is a simple message.")
```

```kotlin
log.error("This has a message with a caught exception.", aCaughtException)
```

```kotlin
log.warn("This has a message, an exception, and a tag.", aCaughtException, "AN_ARBITRARY_TAG")
```

If a message takes a long amount of time to evaluate, it may be passed in as a lambda for
conditional evaluation:

```kotlin
log.info {
    someTimeIntensiveOperation()
    "This message takes a while to evaluate, so it only happens if the INFO level is enabled."
}
```

```kotlin
log.debug(aCaughtException) {
    someTimeIntensiveOperation()
    "Like above, but includes a caught exception."
}
```

```kotlin
log.trace(aCaughtException, "AN_ARBITRARY_TAG") {
    someTimeIntensiveOperation()
    "Like above, but is only evaluated if TRACE is enabled for the provided tag."
}
```
