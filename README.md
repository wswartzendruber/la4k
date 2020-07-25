# Introduction

This is a from-scratch logging API for Kotlin Multiplatform. In principle, `la4k-api` is
combined with a single bridge which is responsible for forwarding logging events from the API to
an actual logging backend. If more than one bridge is present, then the one that appears last
will be used. If no bridge is present, then logging events will simply be discarded.

Currently, only the Android and JVM targets are supported. The JS and various Native targets are
planned for future releases.

# For Libraries

Library authors should use `la4k-api` and only that for dispatching logging events within their
components. Libraries should **never** reference any LA4K bridge directly.

To use `la4k-api` in your library, add it to your project. It is currently hosted on Bintray, so
a new Maven repository will need to be added:

`build.gradle.kts`
```kotlin
repositories {
    maven {
        url = uri("https://dl.bintray.com/wswartzendruber/la4k")
    }
}

dependencies {
    implementation("org.la4k:la4k-api:0.2.0")
}
```

`build.gradle`
```groovy
repositories {
    maven {
        url "https://dl.bintray.com/wswartzendruber/la4k"
    }
}

dependencies {
    implementation("org.la4k:la4k-api:0.2.0")
}
```

And then import the `org.la4k.logger` function:

```kotlin
import org.la4k.logger
```

Instansiate your loggers like so:

```kotlin
val log = logger("my.logger.name")
```

There are six logging levels: **FATAL**, **ERROR**, **WARN**, **INFO**, **DEBUG**, and
**TRACE**. Each logging statement must include a message and may optionally include an exception
and/or a tag of some kind. If a message takes a long amount of time to evaluate, it may be
passed in as a lambda instead.

```kotlin
log.fatal("This is a simple message.")
```

```kotlin
log.error("This has a message with a caught exception.", aCaughtException)
```

```kotlin
log.warn("This has a message, an exception, and a tag.", aCaughtException, "AN_ARBITRARY_TAG")
```

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

# For Applications

If an application has a dependency that uses `la4k-api`, then a bridge can be imported into the
application to have logging messages from the dependency properly forwarded. Bridges are
currently hosted in the same Bintray repo as `la4k-api` and with matching version numbers.

## Android

The `la4k-android` bridge connects `la4k-api` to Android's internal logging system, which can be
viewed using Logcat. This is the only bridge provided for the Android target, in addition to the
Test bridge below. It uses the Java Service Provider Interface to register itself for `la4k-api`
to find. As such, it only needs to be in the classpath during runtime and no other configuration
needs to be performed for it to be activated.

The following level mappings are used:

| LA4K  | Android |
|-------|---------|
| FATAL | ERROR   |
| ERROR | ERROR   |
| WARN  | WARN    |
| INFO  | INFO    |
| DEBUG | DEBUG   |
| TRACE |         |

Note that all TRACE events are discarded by the bridge itself. This is in accordance with
Google's guideline that code outside of development not use VERBOSE logging on the Android side.
As such, the bridge's TRACE level always returns that it is disabled.

Android logging uses what it calls tags to act as the name for whatever is sending a logging
event. This bridge will use the name given to it by `la4k-api`, unless that name exceeds 23
characters. In this case, the first and last ten characters of the name will be joined together
by three periods. So `org.myproject.MyExampleClass` would become `org.myproj...ampleClass`.

As standard Android logging has no concept resembling LA4K tags, they are ignored. Any query for
a level being enabled for a specific tag returns `true` as long as that level is enabled for the
logger in question.

## JVM

Bridges for the JVM use the Java Service Provider Interface to register themselves for
`la4k-api` to find. As such, these bridges only need to be in the classpath during runtime and
no other configuration needs to be performed for them to be activated.

### Apache Log4j

The `la4k-log4j2` bridge connects `la4k-api` to the excellent Apache Log4j engine. Only version
2 is supported as version 1 has been discontinued.

As LA4K's levels were modeled after those from Log4j, no level conversion takes place.

LA4K tags become standalone Log4j markers, which are cached for each `org.la4k.Logger` instance
by this bridge.

### Java Logging

The `la4k-jul` bridge connects `la4k-api` to `java.util.logging`.

The following level mappings are used:

| LA4K  | JUL     |
|-------|---------|
| FATAL | SEVERE  |
| ERROR | SEVERE  |
| WARN  | WARNING |
| INFO  | INFO    |
| DEBUG | FINE    |
| TRACE | FINER   |

As standard Java logging has no concept of tags or markers, they are ignored. Any query for a
level being enabled for a specific tag returns `true` as long as that level is enabled for the
logger in question.

### SLF4J

The `la4k-slf4j` bridge connects `la4k-api` to SLF4J, and therefore, to Logback (if desired).

The following level mappings are used:

| LA4K  | SLF4J |
|-------|-------|
| FATAL | ERROR |
| ERROR | ERROR |
| WARN  | WARN  |
| INFO  | INFO  |
| DEBUG | DEBUG |
| TRACE | TRACE |

LA4K tags become SLF4J markers, which are cached for each `org.la4k.Logger` instance by this
bridge.

## Testing

`la4k-test` is a bridge available for all targets that stores all events in memory. This allows
library authors to ensure that their components are logging expected events, thereby validating
that the desired paths within their code are being taken. This bridge should only be referenced
by a library's unit tests, and never by the library itself.

```kotlin
import org.la4k.logger

import org.la4k.test.clear
import org.la4k.test.count
import org.la4k.test.Event
import org.la4k.test.Level

val log = logger("aLogger")
val expectedEvent("aLogger", Level.FATAL, "something critical happened", caughtException, "TAG")

// Clear the memory store of any existing events.
clear()

log.fatal("something critical happened", caughtException, "TAG")

// This passes because an event exactly like this was just logged exactly once.
assertTrue(count(expectedEvent) == 1)
```

# Licensing

This project is made available under the **Mozilla Public License 2.0** (MPLv2). Simply put,
anyone is free to use this project as-is, to include static linking and even compiling this code
in with code covered by a *different license*. Any changes made to any of this project's files
that are distributed outside of an organization must also be made available under the MPLv2
license. It is **not** viral, though, and will **never** spread across files.

Note that the phrase *different license* refers to even the following FSF licenses:

* GPLv2 and later
* LGPLv2.1 and later
* AGPLv3 and later

In this case, any changes made to any of this project's files must still be made available
under the MPLv2 license.

Please see [LICENSE](LICENSE) and [MPL 2.0 FAQ](https://www.mozilla.org/en-US/MPL/2.0/FAQ/) for
more information.
