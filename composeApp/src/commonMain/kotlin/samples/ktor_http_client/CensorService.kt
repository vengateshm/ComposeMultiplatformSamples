package samples.ktor_http_client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import utils.NetworkError
import utils.Result
import kotlin.coroutines.cancellation.CancellationException

class CensorService(
    private val httpClient: HttpClient
) {
    suspend fun censorWords(uncensored: String): Result<String, NetworkError> {
        val response = try {
            httpClient.get("https://www.purgomalum.com/service/json") {
                parameter("text", uncensored)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: CancellationException) {
            throw e
        }
        return when (response.status.value) {
            in 200..299 -> {
                val censoredText = response.body<CensoredText>()
                Result.Success(censoredText.result)
            }

            400 -> Result.Error(NetworkError.BAD_REQUEST)
            401 -> Result.Error(NetworkError.UN_AUTHORIZED)
            403 -> Result.Error(NetworkError.FORBIDDEN)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}