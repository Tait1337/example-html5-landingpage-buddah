package de.clique.westwood.example.html5.landingpage.buddah

import de.clique.westwood.example.html5.landingpage.buddah.config.*
import de.clique.westwood.example.html5.landingpage.buddah.filter.BasicAuth
import de.clique.westwood.example.html5.landingpage.buddah.filter.loggerFilter
import mu.KotlinLogging
import org.http4k.core.Credentials
import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.routing.ResourceLoader
import org.http4k.server.ApacheServer
import org.http4k.server.asServer

private val LOGGER = KotlinLogging.logger {}

val app: HttpHandler = loggerFilter
        .then(loggerFilter)
        .then(BasicAuth(Regex("^(/album|/gallery).*"), "Password protected area", Credentials(GALLERY_USERNAME, GALLERY_PASSWORD)))
        .then(ServerFilters.ReplaceResponseContentsWithStaticFile.invoke(ResourceLoader.Classpath("/static")))
        .then(routing)

fun main(args: Array<String>) {
    app.asServer(ApacheServer(PORT)).start()
    LOGGER.info { "Server started on port $PORT" }
    LOGGER.info { "Serving albums from $STATIC_FILES_BASEDIR" }
}
