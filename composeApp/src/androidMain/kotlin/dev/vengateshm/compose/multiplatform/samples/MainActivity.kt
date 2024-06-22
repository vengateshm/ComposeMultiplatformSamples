package dev.vengateshm.compose.multiplatform.samples

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import apps.currency_converter_udemy.di.initializeKoin
import apps.whatsapp.model.WindowSize
import apps.whatsapp.model.WindowType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeKoin()
        enableEdgeToEdge()
        setContent {
            App(windowSize = getWindowSize())

            val darkTheme = isSystemInDarkTheme()
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = this.window
                    window.statusBarColor = Color.Transparent.toArgb()
                    window.navigationBarColor = Color.Transparent.toArgb()
                    WindowCompat.getInsetsController(window, view).apply {
                        isAppearanceLightStatusBars = false
                        isAppearanceLightNavigationBars = !darkTheme
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Composable
fun getWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current

    return WindowSize(
        width = when {
            configuration.screenWidthDp < 600 -> WindowType.Compact
            configuration.screenWidthDp < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        },
        height = when {
            configuration.screenHeightDp < 600 -> WindowType.Compact
            configuration.screenHeightDp < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        }
    )
}