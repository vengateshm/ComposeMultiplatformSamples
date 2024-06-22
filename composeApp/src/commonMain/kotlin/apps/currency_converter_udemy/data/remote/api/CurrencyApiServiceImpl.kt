package apps.currency_converter_udemy.data.remote.api

import apps.currency_converter_udemy.domain.model.ApiResponse
import apps.currency_converter_udemy.domain.model.Currency
import apps.currency_converter_udemy.domain.model.CurrencyCode
import apps.currency_converter_udemy.domain.model.RequestState
import apps.currency_converter_udemy.domain.repository.CurrencyApiService
import apps.currency_converter_udemy.domain.repository.PreferencesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CurrencyApiServiceImpl(
    private val preferencesRepository: PreferencesRepository
) : CurrencyApiService {
    companion object {
        const val ENDPOINT = "http://api.currencyapi.com/v3/latest"
        private const val API_KEY = "cur_live_XCeXCpLUjll5Wsa85df82TU1jywUOSvGkhwVsWBl"

        val httpClient by lazy {
            HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 15000L
                }
                install(DefaultRequest) {
                    headers {
                        append("apiKey", API_KEY)
                    }
                }
            }
        }
    }

    override suspend fun getLatestExchangeRates(): RequestState<List<Currency>> {
        return try {
            val response = httpClient.get(ENDPOINT)
            if (response.status.value == 200) {
                println("API RESPONSE : ${response.bodyAsText()}")
                val apiResponse = Json.decodeFromString<ApiResponse>(response.body())

                val availableCurrencyCodes = apiResponse.data.keys
                    .filter {
                        CurrencyCode.entries
                            .map { code -> code.name }
                            .toSet()
                            .contains(it)
                    }

                val availableCurrencies = apiResponse.data.values
                    .filter { currency ->
                        availableCurrencyCodes.contains(currency.code)
                    }

                // Persist timestamp
                val lastUpdated = apiResponse.meta.lastUpdatedAt
                preferencesRepository.saveLastUpdated(lastUpdated = lastUpdated)

                RequestState.Success(data = availableCurrencies)
            } else {
                RequestState.Error(message = "Http error code : ${response.status.value}")
            }
        } catch (e: Exception) {
            RequestState.Error(message = e.message.toString())
        }
    }
}