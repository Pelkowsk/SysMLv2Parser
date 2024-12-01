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

tasks.generateGrammarSource {
    arguments = listOf("-visitor", "-listener", "-package", "org.example")
    outputDirectory = file("build/generated/sources/antlr/main/org/example")
}

sourceSets {
    main {
        java.srcDir(tasks.generateGrammarSource.get().outputDirectory)
    }
    test{
        java.srcDir("src/test/org.example")
    }
}



tasks.named<JavaCompile>("compileJava") {
    dependsOn(tasks.named("generateGrammarSource"))
    source(fileTree("src/main/java/org/example"))
}

tasks.test {
    useJUnitPlatform()
}

defaultTasks("clean", "build")
