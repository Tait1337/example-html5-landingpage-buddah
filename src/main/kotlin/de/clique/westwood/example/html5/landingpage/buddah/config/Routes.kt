package de.clique.westwood.example.html5.landingpage.buddah.config

import de.clique.westwood.example.html5.landingpage.buddah.handler.albumHandler
import de.clique.westwood.example.html5.landingpage.buddah.handler.imageHandler
import org.http4k.core.Method
import org.http4k.routing.*

val routing: RoutingHttpHandler = routes(
        "/album/" bind Method.GET to albumHandler,
        "/album/{albumId}" bind Method.GET to imageHandler,
        static(ResourceLoader.Classpath("/static")),
        static(ResourceLoader.Directory(STATIC_FILES_BASEDIR))
)