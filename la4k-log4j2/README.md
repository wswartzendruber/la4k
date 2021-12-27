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

# Module la4k-log4j2

## Introduction

The `la4k-log4j2` bridge connects `la4k-api` to the excellent Apache Log4j engine. Only version
2 is supported as version 1 has been discontinued.

## Activation

The JAR for this bridge needs to be in the application's classpath. From there, `la4k-api` will
use JSPI to detect it and forward events to it, so long as there is not another bridge that
takes precedence

## Naming

The LA4K name maps directly to the Log4j name.

## Levels

As LA4K's levels were modeled after those from Log4j, no level conversion takes place.

## Tags

LA4K tags become standalone Log4j markers, which are cached for each `org.la4k.Logger` instance
by this bridge.
