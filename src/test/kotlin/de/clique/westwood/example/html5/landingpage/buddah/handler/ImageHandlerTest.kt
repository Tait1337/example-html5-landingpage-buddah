package de.clique.westwood.example.html5.landingpage.buddah.handler

import de.clique.westwood.example.html5.landingpage.buddah.entity.Album
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class ImageHandlerTest {

    val handle = albumHandler // test component only
    //val handle = app // uncomment to test whole application
    //val handle = ClientFilters.SetHostFrom(Uri.of("http://localhost").port(PORT)).then(JavaHttpClient())  // uncomment to test on external server

    @Test
    fun `handler lists sample albums`() {
        val input = Request(Method.GET, "/gallery")

        val sampleResponse = "[{\"id\":\"Buddah\",\"title\":\"Buddah\",\"release\":\"2020-04-23T06:58:59.401496\",\"img\":\"image1.jpg\"},{\"id\":\"Zen_Garden\",\"title\":\"Zen Garden\",\"release\":\"2020-04-23T06:58:59.323219\",\"img\":\"image4.png\"}]"
        val expectedResponse = Response(OK).body(sampleResponse)

        val actual = handle(input)
        val actualResponse = Json.decodeFromString(ListSerializer(Album.serializer()), actual.bodyString())

        assertEquals(actual.status, expectedResponse.status)
        assertEquals(actualResponse.size, 2)
        for (album in actualResponse) {
            assertNotNull(album.id)
            assertNotNull(album.title)
            if (album.id == "Zen_Garden") {
                assertEquals(album.title, "Zen Garden")
            }
        }
    }
}