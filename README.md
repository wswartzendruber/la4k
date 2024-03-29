<!--
    Copyright 2021 William Swartzendruber

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.

    SPDX-License-Identifier: Apache-2.0
-->

# Introduction

LA4K is a from-scratch logging API for Kotlin Multiplatform. It contains two main parts:

1. `la4k-api`, which **libraries** use to log events in an agnostic manner.
2. Any one of the bridges which **applications** use to forward those events to a logging
   implementation.

# For Libraries

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

With the module added to your project, you can continue on to the [la4k-api module](la4k-api)
documentation.

# For Applications

If an application has a dependency that uses `la4k-api`, then a bridge can be imported into the
application to have logging messages from the dependency properly forwarded. Bridges are
currently hosted in the same Bintray repo as `la4k-api` and with matching version numbers.

Note that if no bridge has been activated then logging events will simply be discarded.

## JVM

When running on the JVM, LA4K uses the Java Service Provider Interface to connect the API to a
single bridge. As such, the chosen bridge need only be in the classpath during startup. If more
than one bridge is present, the last one discovered will be used.

The following bridges are available for the JVM:

| Module                       | Gradle Syntax                                   |
|------------------------------|-------------------------------------------------|
| [la4k-console](la4k-console) | `implementation("org.la4k:la4k-console:0.5.0")` |
| [la4k-jul](la4k-jul)         | `implementation("org.la4k:la4k-jul:0.5.0")`     |
| [la4k-log4j2](la4k-log4j2)   | `implementation("org.la4k:la4k-log4j2:0.5.0")`  |
| [la4k-slf4j](la4k-slf4j)     | `implementation("org.la4k:la4k-slf4j:0.5.0")`   |
| [la4k-test](la4k-test)       | `implementation("org.la4k:la4k-test:0.5.0")`    |

## Android

When running on Android, LA4K uses the Java Service Provider Interface to connect the API to a
single bridge. As such, the chosen bridge need only be in the classpath during startup. If more
than one bridge is present, the last one discovered will be used.

The following bridge is available for Android:

| Module                       | Gradle Syntax                                   |
|------------------------------|-------------------------------------------------|
| [la4k-android](la4k-android) | `implementation("org.la4k:la4k-android:0.5.0")` |

## JS

When it comes to JavaScript, `la4k-api` provides the `org.la4k.activateBridge` function for
activating a bridge of choice. It can only be called once and must be called before any logging
statement is made by `la4k-api`.

### Browser

The following bridges are available for the browser:

| Module                       | Gradle Syntax                                   | Activation
|------------------------------|-------------------------------------------------|-------------------------------------------------|
| [la4k-console](la4k-console) | `implementation("org.la4k:la4k-console:0.5.0")` | `activateBridge(org.la4k.test.ConsoleBridge())` |
| [la4k-test](la4k-test)       | `implementation("org.la4k:la4k-test:0.5.0")`    | `activateBridge(org.la4k.test.TestBridge())`    |

### NodeJS

The following bridges are available for NodeJS:

| Module                       | Gradle Syntax                                   | Activation                                      |
|------------------------------|-------------------------------------------------|-------------------------------------------------|
| [la4k-console](la4k-console) | `implementation("org.la4k:la4k-console:0.5.0")` | `activateBridge(org.la4k.test.ConsoleBridge())` |
| [la4k-test](la4k-test)       | `implementation("org.la4k:la4k-test:0.5.0")`    | `activateBridge(org.la4k.test.TestBridge())`    |
| [la4k-winston](la4k-winston) | `implementation("org.la4k:la4k-winston:0.5.0")` | `activateBridge(org.la4k.test.WinstonBridge())` |

# Licensing

Different portions of this project are placed under different licenses:

| Component     | License            | SPDX ID                                 |
|---------------|--------------------|-----------------------------------------|
| Library       | Apache License 2.0 | [`Apache-2.0`](LICENSES/Apache-2.0.txt) |
| Documentation | Apache License 2.0 | [`Apache-2.0`](LICENSES/Apache-2.0.txt) |
| Build scripts | Public Domain      | [`CC0-1.0`](LICENSES/CC0-1.0.txt)       |
| Unit tests    | Public Domain      | [`CC0-1.0`](LICENSES/CC0-1.0.txt)       |
