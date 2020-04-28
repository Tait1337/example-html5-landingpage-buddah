package de.clique.westwood.example.html5.landingpage.buddah.entity

import kotlinx.serialization.*
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

@Serializable
data class Album(val id: String, val title: String, @Serializable(with=OffsetDateTimeSerializer::class) val release: OffsetDateTime, val img: String)

// Custom DateTime serializer - can be replaced with upcoming Kotlin default
@Serializer(forClass = OffsetDateTime::class)
object OffsetDateTimeSerializer : KSerializer<OffsetDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveDescriptor("OffsetDateTime", PrimitiveKind.STRING)

    override fun serialize(output: Encoder, obj: OffsetDateTime) {
        output.encodeString(obj.toInstant().toEpochMilli().toString())
    }

    override fun deserialize(input: Decoder): OffsetDateTime {
        return OffsetDateTime.ofInstant(Instant.ofEpochMilli(input.decodeString().toLong()), ZoneId.systemDefault())
    }
}

