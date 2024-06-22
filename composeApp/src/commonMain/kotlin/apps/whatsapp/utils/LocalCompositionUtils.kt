package apps.whatsapp.utils

import androidx.compose.runtime.staticCompositionLocalOf
import apps.whatsapp.model.WindowSize

val LocalWindowSize = staticCompositionLocalOf<WindowSize> {
    error("CompositionLocal LocalWindowSize not present")
}