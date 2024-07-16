import android.app.Application
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import apps.whatsapp.model.WindowSize
import apps.whatsapp.model.WindowType
import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.httpFetcher
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.batikSvgDecoder
import io.kamel.image.config.resourcesFetcher
import samples.di.koin.initKoinDi
import java.awt.Dimension

fun main() = application {
    initFirebase()
    initKoinDi()
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

        }
    }
}

private fun initFirebase() {
    FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
        val storage = mutableMapOf<String, String>()
        override fun clear(key: String) {
            storage.remove(key)
        }

        override fun log(msg: String) = println(msg)

        override fun retrieve(key: String) = storage[key]

        override fun store(key: String, value: String) = storage.set(key, value)

    })

    val options = FirebaseOptions(
        projectId = "",
        applicationId = "",
        apiKey = ""
    )

    Firebase.initialize(Application(), options)
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