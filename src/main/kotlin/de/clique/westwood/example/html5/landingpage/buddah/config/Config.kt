package de.clique.westwood.example.html5.landingpage.buddah.config

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties

private val config = systemProperties() overriding
        EnvironmentVariables() overriding
        ConfigurationProperties.fromResource("application.properties")

val PORT = config[Key("server.port", intType)]
val STATIC_FILES_BASEDIR = config[Key("files.base.dir", stringType)]
val GALLERY_USERNAME = config[Key("gallery.username", stringType)]
val GALLERY_PASSWORD = config[Key("gallery.password", stringType)]

