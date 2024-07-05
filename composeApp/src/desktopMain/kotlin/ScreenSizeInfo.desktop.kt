import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    return ScreenSizeInfo(
        hPX = windowInfo.containerSize.height,
        wPX = windowInfo.containerSize.width,
        hDP = with(density) { windowInfo.containerSize.height.toDp() },
        wDP = with(density) { windowInfo.containerSize.width.toDp() }
    )
}