package de.clique.westwood.example.html5.landingpage.buddah.handler

import de.clique.westwood.example.html5.landingpage.buddah.config.STATIC_FILES_BASEDIR
import de.clique.westwood.example.html5.landingpage.buddah.entity.Album
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.lens.Path
import org.http4k.lens.string
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.time.OffsetDateTime
import java.time.ZoneId
import kotlin.random.Random

val albumHandler: HttpHandler = { _ ->
    Response(Status.OK).body(Json.encodeToString(ListSerializer(Album.serializer()), getAllAlbums()))
}

private fun getAllAlbums(): List<Album> {
    return Files.list(Paths.get("$STATIC_FILES_BASEDIR/album/"))
            .map { it.toFile() }
            .filter { it.isDirectory }
            .map {
                Album(
                        it.name,
                        it.name.replace("_", " "),
                        OffsetDateTime.ofInstant(
                                Files.readAttributes(it.toPath(), BasicFileAttributes::class.java).creationTime().toInstant(),
                                ZoneId.systemDefault()
                        ), getOneRandomImage(it.name))
            }
            .toList()
}

private fun getOneRandomImage(albumId: String): String {
    val allImages = getAllImages(albumId)
    val rndInt = Random.nextInt(allImages.size)
    return allImages[rndInt]
}

val imageHandler: HttpHandler = { req ->
    val albumId = Path.string().of("albumId").extract(req)
    Response(Status.OK).body(Json.encodeToString(ListSerializer(String.serializer()), getAllImages(albumId)))
}

private fun getAllImages(albumId: String): List<String> {
    return File("$STATIC_FILES_BASEDIR/album/$albumId/thumbnails")
            .walk()
            .filter { it.isFile }
            .map { it.name }
            .toList()
}


