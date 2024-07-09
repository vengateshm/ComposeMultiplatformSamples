package samples.ktor_http_client

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)
