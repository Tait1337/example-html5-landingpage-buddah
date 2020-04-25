package de.clique.westwood.example.html5.landingpage.buddah.handler

import de.clique.westwood.example.html5.landingpage.buddah.config.STATIC_FILES_BASEDIR
import de.clique.westwood.example.html5.landingpage.buddah.entity.Album
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import org.http4k.lens.Path
import org.http4k.lens.string
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.random.Random
import kotlin.streams.toList


val albumHandler: HttpHandler = { _ ->
    val albumListLens = Body.auto<List<Album>>().toLens()
    Response(Status.OK).with(
            albumListLens of getAllAlbums()
    )
}

private fun getAllAlbums(): List<Album> {
    return Files.list(java.nio.file.Paths.get("$STATIC_FILES_BASEDIR/album/"))
            .map { it.toFile() }
            .filter { it.isDirectory }
            .map { Album(
                    it.name,
                    it.name.replace("_"," "),
                    LocalDateTime.ofInstant(
                            Files.readAttributes(it.toPath(), BasicFileAttributes::class.java).creationTime().toInstant(),
                            ZoneId.systemDefault()
                    ), getOneRandomImage(it.name)) }
            .toList()
}

private fun getOneRandomImage(albumId: String): String {
    val allImages = getAllImages(albumId)
    return allImages[Random.nextInt(0, allImages.size - 1)]
}

val imageHandler: HttpHandler = { req ->
    val imageListLens = Body.auto<List<String>>().toLens()
    val albumId = Path.string().of("albumId").extract(req)
    Response(Status.OK).with(
            imageListLens of getAllImages(albumId)
    )
}

private fun getAllImages(albumId: String): List<String> {
    return File("$STATIC_FILES_BASEDIR/album/$albumId/thumbnails")
            .walk()
            .filter { it.isFile }
            .map { it.name }
            .toList()
}


