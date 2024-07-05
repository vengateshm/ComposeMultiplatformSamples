import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import apps.energy_insight_dashboard.EnergyAppEntryPoint
import apps.whatsapp.model.WindowSize
import apps.whatsapp.model.WindowType
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.batikSvgDecoder
import io.kamel.image.config.resourcesFetcher
import java.awt.Dimension

fun main() = application {
    val state = rememberWindowState(
        width = 800.dp, height = 600.dp,
        placement = WindowPlacement.Floating
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "ComposeMultiplatformSamples",
        state = state,
        resizable = true
    ) {
        window.minimumSize = Dimension(800, 600)

        val desktopConfig = KamelConfig {
            takeFrom(KamelConfig.Default)
            httpFetcher()
            // Available only on Desktop.
            resourcesFetcher()
            // Available only on Desktop.
            // An alternative svg decoder
            batikSvgDecoder()
        }
        CompositionLocalProvider(LocalKamelConfig provides desktopConfig) {
            App()
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