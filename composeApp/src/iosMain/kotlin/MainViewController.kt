import androidx.compose.ui.window.ComposeUIViewController
import apps.whatsapp.model.WindowSize
import apps.whatsapp.model.WindowType

fun MainViewController(width: () -> Int, height: () -> Int) =
    ComposeUIViewController { App(getWindowSize(width(), height())) }

fun getWindowSize(width: Int, height: Int): WindowSize {
    return WindowSize(
        width = when {
            width < 600 -> WindowType.Compact
            width < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        },
        height = when {
            height < 600 -> WindowType.Compact
            height < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        }
    )
}