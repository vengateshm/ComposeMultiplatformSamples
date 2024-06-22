package apps.currency_converter_udemy.data.local

import apps.currency_converter_udemy.domain.model.Currency
import apps.currency_converter_udemy.domain.model.CurrencyEntity
import apps.currency_converter_udemy.domain.model.RequestState
import apps.currency_converter_udemy.domain.model.toCurrency
import apps.currency_converter_udemy.domain.model.toCurrencyEntity
import apps.currency_converter_udemy.domain.repository.LocalDbRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MongoLocalDbRepositoryImpl : LocalDbRepository {
    private var realm: Realm? = null

    init {
        configureDb()
    }

    override fun configureDb() {
        if (realm == null || realm!!.isClosed()) {
            val config = RealmConfiguration.Builder(
                schema = setOf(CurrencyEntity::class)
            )
                .compactOnLaunch()
                .build()
            realm = Realm.open(config)
        }
    }

    override suspend fun insertCurrencyData(currency: Currency) {
        realm?.write { copyToRealm(currency.toCurrencyEntity()) }
    }

    override fun readCurrencyData(): Flow<RequestState<List<Currency>>> {
        return realm?.query<CurrencyEntity>()
            ?.asFlow()
            ?.map { RequestState.Success(data = it.list.map { currencyEntity -> currencyEntity.toCurrency() }) }
            ?: flow { RequestState.Error(message = "Realm not configured") }
    }

    override suspend fun cleanUp() {
        realm?.write {
            val currencyCollection = this.query<CurrencyEntity>()
            delete(currencyCollection)
        }
    }
}