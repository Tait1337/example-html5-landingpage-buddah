package de.clique.westwood.example.html5.landingpage.buddah.filter

import org.http4k.base64Decoded
import org.http4k.core.*

/**
 * Simple Basic Auth credential checking.
 */
object BasicAuth {

    operator fun invoke(pathRegEx: Regex = Regex(".*"), realm: String, authorize: (Credentials) -> Boolean) = Filter { next ->
        {
            val credentials = it.basicAuthenticationCredentials()
            if (it.uri.toString().matches(pathRegEx) && (credentials == null || !authorize(credentials))) {
                Response(Status.UNAUTHORIZED).header("WWW-Authenticate", "Basic Realm=\"$realm\"")
            } else next(it)
        }
    }

    /**
     * Static credentials validation
     */
    operator fun invoke(pathRegEx: Regex = Regex(".*"), realm: String, credentials: Credentials) = this(pathRegEx, realm) { it == credentials }

    private fun Request.basicAuthenticationCredentials(): Credentials? = header("Authorization")
            ?.trim()
            ?.takeIf { it.startsWith("Basic") }
            ?.substringAfter("Basic")
            ?.trim()
            ?.toCredentials()

    private fun String.toCredentials(): Credentials? = base64Decoded().split(":").let { Credentials(it.getOrElse(0) { "" }, it.getOrElse(1) { "" }) }
}