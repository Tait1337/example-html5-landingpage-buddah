plugins {
    application
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.sonarqube") version "3.3"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("de.clique.westwood.example.html5.landingpage.buddah.AppKt")
    group = "de.clique.westwood.example.html5.landingpage.buddah"
    version = "1.0.0-SNAPSHOT"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx","kotlinx-serialization-json","1.3.2")
    implementation("org.http4k", "http4k-core", "4.25.13.0") // Apache License 2.0
    implementation("org.http4k", "http4k-server-apache", "4.25.13.0") // Apache License 2.0
    implementation("org.http4k", "http4k-client-okhttp", "4.25.13.0") // Apache License 2.0
    implementation("com.natpryce", "konfig", "1.6.10.0") // Apache License 2.0
    implementation("io.github.microutils", "kotlin-logging", "2.1.21") // Apache License 2.0
    implementation("org.slf4j", "slf4j-simple", "1.7.36") // MIT License

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.8.2")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.shadowJar {
    manifest {
        attributes("Main-Class" to application.mainClass)
    }
    mergeServiceFiles()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "Tait1337_example-html5-landingpage-buddah")
        property("sonar.organization", "tait1337")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}