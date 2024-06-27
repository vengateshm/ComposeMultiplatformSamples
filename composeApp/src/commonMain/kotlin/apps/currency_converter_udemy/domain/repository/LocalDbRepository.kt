package apps.currency_converter_udemy.domain.repository

import apps.currency_converter_udemy.domain.model.Currency
import apps.currency_converter_udemy.domain.model.RequestState
import kotlinx.coroutines.flow.Flow

interface LocalDbRepository {
    fun configureDb()
    suspend fun insertCurrencyData(currency: Currency)
    fun readCurrencyData(): Flow<RequestState<List<Currency>>>
    suspend fun cleanUp()
}