/*
 * Copyright 2020 William Swartzendruber
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a
 * copy of the MPL was not distributed with this file, You can obtain one at
 * https://mozilla.org/MPL/2.0/.
 */

plugins {
   kotlin("multiplatform").version("1.3.61").apply(false)
   kotlin("jvm").version("1.3.61").apply(false)
}

allprojects {
   repositories {
      jcenter()
   }
}
