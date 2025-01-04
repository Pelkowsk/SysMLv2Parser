/*****************************************************************************
 * SysML 2 Pilot Implementation
 * Copyright (c) 2018-2024 Model Driven Solutions, Inc.
 * Copyright (c) 2018-2024 IncQuery Labs Ltd.
 * Copyright (c) 2019-2023 Maplesoft (Waterloo Maple, Inc.)
 * Copyright (c) 2019-2023 Mgnite Inc.
 * Copyright (c) 2024-2025 [Your Organization]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 * Contributors:
 *  Ed Seidewitz, MDS
 *  Zoltan Kiss, IncQuery
 *  Balazs Grill, IncQuery
 *  Hisashi Miyashita, Maplesoft/Mgnite
 *
 *****************************************************************************/

plugins {
    id("java")
    id("antlr")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.0")
    implementation("org.antlr:antlr4-runtime:4.13.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

// Konfiguration der generateGrammarSource-Task
tasks.generateGrammarSource {
    arguments.addAll( listOf("-visitor", "-listener", "-package", "omg"))
    //outputDirectory = file("build/generated/sources/antlr/main/org/excample")
}

// Aufnahme der generierten Quellen in die Source-Sets
sourceSets {
    main {
        java {
            srcDirs(tasks.generateGrammarSource.get().outputDirectory)
        }
    }
    test {
        java {
            srcDirs(tasks.generateGrammarSource.get().outputDirectory)
        }
    }
}

// Sicherstellen, dass die generierten Quellen vor dem Kompilieren bereitstehen
tasks.named<JavaCompile>("compileJava") {
    dependsOn(tasks.named("generateGrammarSource"))
}

// Sicherstellen, dass Tests nach der Generierung der Quellen laufen
tasks.test {
    useJUnitPlatform()
    dependsOn(tasks.named("generateGrammarSource"))
}

// Standard-Tasks definieren
defaultTasks("clean", "build")
