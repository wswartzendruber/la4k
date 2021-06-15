<!--
    SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>

    SPDX-License-Identifier: CC-BY-4.0
-->

# Module la4k-api

`la4k-api` is used by libraries to log events in an entirely agnostic manner. In this way, the
library should not have to take any specific logging implementation into consideration.

The single `org.la4k` package in this module contains all necessary functionality.

# Package org.la4k

## Initialization

To use this library, import the `org.la4k.logger` function:

```kotlin
import org.la4k.logger
```

Then instansiate a new logger by calling it with the desired name as the only parameter:

```kotlin
val log = logger("io.mypackage.OneOfMyClasses")
```

As Kotlin Multiplatform gains better reflection support, logger instansiation should become more
intuitive and remove the need to specify the logger's name.

Note that the `logger` function caches each instance it creates. Future calls with the same name
will simply involve retrieving an existing logger from a dictionary.

## Usage

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
conditional execution:

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

Note that the message may be of any type. This affords each bridge the opportunity to handle
each message in a manner most appropriate for it.

See `org.la4k.Logger` for more information.
