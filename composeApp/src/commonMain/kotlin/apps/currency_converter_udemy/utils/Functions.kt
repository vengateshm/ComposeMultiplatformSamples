package apps.currency_converter_udemy.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.bebas_neue_regular
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

fun calculateExchangeRate(source: Double, target: Double): Double {
    return target / source
}

fun convertAmount(amount: Double, exchangeRate: Double): Double {
    return amount * exchangeRate
}

fun displayCurrentDateTime(): String {
    val now = Clock.System.now()
    val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())

    val dayOfMonth = localDateTime.date.dayOfMonth
    val month = localDateTime.month.toString().lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    val year = localDateTime.date.year

    val suffix = when {
        dayOfMonth in 11..13 -> "th" // special case
        dayOfMonth % 10 == 1 -> "st"
        dayOfMonth % 10 == 2 -> "nd"
        dayOfMonth % 10 == 3 -> "rd"
        else -> "th"
    }

    return "$dayOfMonth$suffix $month, $year"
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CustomFontFamily() = FontFamily(Font(resource = Res.font.bebas_neue_regular))
