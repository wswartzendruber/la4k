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

# Module la4k-android

## Introduction

The `la4k-android` bridge connects `la4k-api` to Android's internal logging system, which can be
viewed using Logcat.

## Activation

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence.

## Naming

Android logging uses what it calls tags to act as the name for whatever is sending a logging
event. This bridge will use the name given to it by `la4k-api`, unless that name exceeds 23
characters. In this case, the first and last ten characters of the name will be joined together
by three periods. So `org.myproject.MyExampleClass` would become `org.myproj...ampleClass`.

## Levels

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

## Tags

As standard Android logging has no concept resembling LA4K tags, they are ignored. Any query for
a level being enabled for a specific tag returns `true` as long as that level is enabled for the
logger in question.
