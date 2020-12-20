plugins {
    application
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("org.sonarqube") version "3.0"
}

repositories {
    mavenCentral()
}

application {
    mainClassName = "de.clique.westwood.example.html5.landingpage.buddah.AppKt"
    group = "de.clique.westwood.example.html5.landingpage.buddah"
    version = "1.0.0-SNAPSHOT"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx","kotlinx-serialization-core","1.0.0-RC")
    implementation("org.http4k", "http4k-core", "3.258.0") // Apache License 2.0
    implementation("org.http4k", "http4k-server-apache", "3.258.0") // Apache License 2.0
    implementation("org.http4k", "http4k-client-okhttp", "3.258.0") // Apache License 2.0
    implementation("com.natpryce", "konfig", "1.6.10.0") // Apache License 2.0
    implementation("io.github.microutils", "kotlin-logging", "1.8.3") // Apache License 2.0
    implementation("org.slf4j", "slf4j-simple", "1.7.30") // MIT License

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.2")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.shadowJar {
    manifest {
        attributes("Main-Class" to application.mainClassName)
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