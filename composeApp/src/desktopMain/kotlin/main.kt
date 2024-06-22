import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import apps.whatsapp.model.WindowSize
import apps.whatsapp.model.WindowType
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.batikSvgDecoder
import io.kamel.image.config.resourcesFetcher

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMultiplatformSamples",
    ) {

        val desktopConfig = KamelConfig {
            takeFrom(KamelConfig.Default)
            httpFetcher()
            // Available only on Desktop.
            resourcesFetcher()
            // Available only on Desktop.
            // An alternative svg decoder
            batikSvgDecoder()
        }
        CompositionLocalProvider(LocalKamelConfig provides desktopConfig){
            App(windowSize = getWindowSize())
        }
    }
}

fun getWindowSize(): WindowSize {
    val windowState = WindowState()

    return WindowSize(
        width = when {
            windowState.size.width.value.toInt() < 600 -> WindowType.Compact
            windowState.size.width.value.toInt() < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        },
        height = when {
            windowState.size.height.value.toInt() < 600 -> WindowType.Compact
            windowState.size.height.value.toInt() < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        }
    ).also {
        println("Desktop window size : ${windowState.size}")
    }
}