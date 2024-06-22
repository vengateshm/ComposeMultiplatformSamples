package apps.currency_converter_udemy

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import apps.currency_converter_udemy.di.initializeKoin
import apps.currency_converter_udemy.presentation.screens.HomeScreen
import apps.currency_converter_udemy.ui.theme.darkScheme
import apps.currency_converter_udemy.ui.theme.lightScheme
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun CurrencyConverterMain(modifier: Modifier = Modifier) {
    // Using custom color schemes
    /*val colorScheme = if (!isSystemInDarkTheme()) lightScheme else darkScheme
    MaterialTheme(colorScheme = colorScheme) {

    }*/
    //initializeKoin()
    MaterialTheme {
        Navigator(HomeScreen())
    }
}