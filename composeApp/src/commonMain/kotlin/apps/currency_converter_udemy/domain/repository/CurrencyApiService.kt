package apps.currency_converter_udemy.domain.repository

import apps.currency_converter_udemy.domain.model.Currency
import apps.currency_converter_udemy.domain.model.RequestState

interface CurrencyApiService {
    suspend fun getLatestExchangeRates(): RequestState<List<Currency>>
}