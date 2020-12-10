# Introduction

LA4K is a from-scratch logging API for Kotlin Multiplatform. It decouples the API that libraries
use to log events from the bridges that applications include to forward those events to actual
logging implementations.

# Libraries

Libraries reference `la4k-api` in the same manner regardless of what Kotlin backend they target:

`build.gradle.kts`
```kotlin
repositories {
    maven {
        url = uri("https://dl.bintray.com/wswartzendruber/la4k")
    }
}

dependencies {
    implementation("org.la4k:la4k-api:0.5.0")
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
    implementation("org.la4k:la4k-api:0.5.0")
}
```

With the module added to your project, you can continue on to the
[la4k-api module documentation](la4k-api/README.md).

# Applications

If an application has a dependency that uses `la4k-api`, then a bridge can be imported into the
application to have logging messages from the dependency properly forwarded. Bridges are
currently hosted in the same Bintray repo as `la4k-api` and with matching version numbers.

## JVM

When running on the JVM, LA4K uses the Java Service Provider Interface to connect the API to a
single bridge. As such, the chosen bridge need only be in the classpath during startup. If more
than one bridge is present, the last one discovered will be used.

The following bridges are available for the JVM:

| Module                               | Gradle Syntax                                  |
|--------------------------------------|------------------------------------------------|
| [la4k-jul](la4k-jul/README.md)       | `implementation("org.la4k:la4k-jul:0.5.0")`    |
| [la4k-log4j2](la4k-log4j2/README.md) | `implementation("org.la4k:la4k-log4j2:0.5.0")` |
| [la4k-slf4j](la4k-slf4j/README.md)   | `implementation("org.la4k:la4k-slf4j:0.5.0")`  |
| [la4k-test](la4k-test/README.md)     | `implementation("org.la4k:la4k-test:0.5.0")`   |

## Android

When running on Android, LA4K uses the Java Service Provider Interface to connect the API to a
single bridge. As such, the chosen bridge need only be in the classpath during startup. If more
than one bridge is present, the last one discovered will be used.

The following bridge is available for Android:

| Module                                 | Gradle Syntax                                   |
|----------------------------------------|-------------------------------------------------|
| [la4k-android](la4k-android/README.md) | `implementation("org.la4k:la4k-android:0.5.0")` |

## JS

When it comes to JavaScript, `la4k-api` provides the `org.la4k.activateBridge` function for
activating a bridge of choice. It can only be called once and must be called before any logging
statement is made by `la4k-api`.

### Browser

The following bridge is available for the browser:

| Module                           | Gradle Syntax                                | Activation
|----------------------------------|----------------------------------------------|----------------------------------------------|
| [la4k-test](la4k-test/README.md) | `implementation("org.la4k:la4k-test:0.5.0")` | `activateBridge(org.la4k.test.TestBridge())` |

### NodeJS

The following bridge is available for NodeJS:

| Module                           | Gradle Syntax                                | Activation                                   |
|----------------------------------|----------------------------------------------|----------------------------------------------|
| [la4k-test](la4k-test/README.md) | `implementation("org.la4k:la4k-test:0.5.0")` | `activateBridge(org.la4k.test.TestBridge())` |
