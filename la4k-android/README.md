<!--
    SPDX-FileCopyrightText: 2021 William Swartzendruber <wswartzendruber@gmail.com>

    SPDX-License-Identifier: CC-BY-4.0
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
