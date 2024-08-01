package samples.datetime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

data class City(val name: String, val timeZone: TimeZone)

@Composable
fun DateTimeSample(modifier: Modifier = Modifier) {

    val cities = remember {
        listOf(
            City(name = "London", timeZone = TimeZone.of("Asia/Kolkata")),
//            City(name = "London", timeZone = TimeZone.of("Europe/London")),
        )
    }

    var cityTimes by remember {
        mutableStateOf(listOf<Pair<City, LocalDateTime>>())
    }
    LaunchedEffect(true) {
        while (true) {
            cityTimes = cities.map {
                it to Clock.System.now().toLocalDateTime(it.timeZone)
            }
            delay(1000L)
        }
    }
    cityTimes.firstOrNull()?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = it.first.name)
            Text(text = it.second.format(
                LocalDateTime.Format {
                    hour()
                    char(':')
                    minute()
                    char(':')
                    second()
                }
            ))
            Text(text = it.second.format(
                LocalDateTime.Format {
                    dayOfMonth()
                    char('/')
                    monthNumber()
                    char('/')
                    year()
                }
            ))
        }
    }
}