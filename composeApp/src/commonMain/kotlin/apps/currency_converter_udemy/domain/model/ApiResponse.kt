package apps.currency_converter_udemy.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

@Serializable
data class ApiResponse(
    val meta: MetaData,
    val data: Map<String, Currency>
)

@Serializable
data class MetaData(
    @SerialName("last_updated_at")
    val lastUpdatedAt: String
)

@Serializable
data class Currency(
    var code: String,
    var value: Double
)

open class CurrencyEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var code: String = ""
    var value: Double = 0.0
}

fun Currency.toCurrencyEntity() = CurrencyEntity()
    .apply {
        code = this@toCurrencyEntity.code
        value = this@toCurrencyEntity.value
    }

fun CurrencyEntity.toCurrency() = Currency(
    code = this.code,
    value = this.value
)