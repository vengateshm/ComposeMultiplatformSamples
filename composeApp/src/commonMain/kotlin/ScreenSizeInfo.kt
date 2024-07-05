import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Getting screen size info for UI-related calculations */
data class ScreenSizeInfo(val hPX: Int, val wPX: Int, val hDP: Dp, val wDP: Dp)

enum class WindowType {
    CompactWidth
}

fun ScreenSizeInfo.isZeroWidth() = this.wDP in 0.dp..<24.dp
fun ScreenSizeInfo.isCompactWidth() = this.wDP in 24.dp..<600.dp

@Composable
expect fun getScreenSizeInfo(): ScreenSizeInfo