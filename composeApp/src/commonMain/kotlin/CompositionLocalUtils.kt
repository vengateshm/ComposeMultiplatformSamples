import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import apps.whatsapp.model.WindowSize

val LocalWindowSize = staticCompositionLocalOf<WindowSize> {
    error("CompositionLocal LocalWindowSize not present")
}

val LocalScreenSize = compositionLocalOf<ScreenSizeInfo> {
    error("No Screen Size Info provided")
}