package apps.whatsapp

import LocalWindowSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import apps.whatsapp.desktop.DesktopMain
import apps.whatsapp.mobile.AndroidMain
import apps.whatsapp.model.WindowSize
import apps.whatsapp.model.WindowType

@Composable
fun WhatsAppMain(modifier: Modifier = Modifier, windowSize: WindowSize? = null) {
    MaterialTheme {
        windowSize?.run {
            CompositionLocalProvider(LocalWindowSize provides windowSize) {
                when (windowSize.width) {
                    WindowType.Compact -> {
                        AndroidMain()
                    }

                    WindowType.Medium -> {
                        DesktopMain()
                    }

                    else -> {
                        DesktopMain()
                    }
                }
            }
        }
    }
}