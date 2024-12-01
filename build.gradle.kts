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
    arguments = listOf("-visitor", "-listener", "-package", "org.example")
    outputDirectory = file("build/generated/sources/antlr/main/org/example")
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
            // Zugriff auf die generierten Quellen auch für Tests
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
