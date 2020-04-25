package de.clique.westwood.example.html5.landingpage.buddah.filter

import mu.KotlinLogging
import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import java.time.Clock

private val LOGGER = KotlinLogging.logger {}

val loggerFilter: Filter = Filter { next: HttpHandler ->
    { req ->
        var clock = Clock.systemUTC();
        val start = clock.millis()
        val response = next(req)
        val latency = clock.millis() - start
        LOGGER.debug { "${Clock.systemUTC().instant()} Request to ${req.uri} took ${latency}ms and was handled by ${next.javaClass}" }
        response
    }
}