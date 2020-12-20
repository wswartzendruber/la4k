# Module la4k-test

## Introduction

`la4k-test` is a bridge available for all targets that stores all events in memory. This allows
library authors to ensure that their components are logging events as expected, thereby
validating that the desired code paths are being taken.

This bridge should only be referenced by a library's unit tests and never by the library itself.

## Activation

### JVM, Android

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence.

### JS

Activation is done via the `org.la4k.activateBridge` function:

```kotlin
import org.la4k.activateBridge
```

```kotlin
activateBridge(org.la4k.test.TestBridge())
```

This must be done before any LA4K loggers are initialized by any component. Otherwise, all LA4K
logging will be discarded.

## Usage

```kotlin
import org.la4k.logger

import org.la4k.test.clear
import org.la4k.test.count
import org.la4k.test.Event
import org.la4k.test.Level

val log = logger("aLogger")
val expectedEvent = Event("aLogger", Level.FATAL, "something critical", caughtException, "TAG")

// Clear the memory store of any existing events.
clear()

log.fatal("something critical", caughtException, "TAG")

// This passes because an event exactly like this was just logged exactly once.
assertTrue(
    count({ it == expectedEvent }) == 1
)
```

## Levels and Tags

All levels for all loggers and tags will always return that they are enabled.
