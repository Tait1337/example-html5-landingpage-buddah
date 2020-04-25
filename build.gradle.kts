plugins {
    application
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version "5.2.0"
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
    implementation("org.http4k", "http4k-core", "3.242.0") // Apache License 2.0
    implementation("org.http4k", "http4k-server-apache", "3.242.0") // Apache License 2.0
    implementation("org.http4k", "http4k-client-okhttp", "3.242.0") // Apache License 2.0
    //implementation("org.http4k","http4k-format-kotlinx-serialization","3.242.0") // Apache License 2.0
    implementation("org.http4k", "http4k-format-jackson", "3.242.0") // Apache License 2.0
    implementation("com.natpryce", "konfig", "1.6.10.0") // Apache License 2.0
    implementation("io.github.microutils", "kotlin-logging", "1.7.9") // Apache License 2.0
    implementation("org.slf4j", "slf4j-simple", "1.7.29") // MIT License

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.1")
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
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