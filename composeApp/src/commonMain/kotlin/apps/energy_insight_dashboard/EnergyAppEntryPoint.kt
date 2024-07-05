package apps.energy_insight_dashboard

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import apps.energy_insight_dashboard.screens.desktop.EnergyAppDesktopEntryPoint
import LocalScreenSize
import getScreenSizeInfo
import isCompactWidth
import isZeroWidth

@Composable
fun EnergyAppEntryPoint(modifier: Modifier = Modifier) {
    MaterialTheme {
        val screenSizeInfo = getScreenSizeInfo()
        println(screenSizeInfo)
        CompositionLocalProvider(
            LocalScreenSize provides getScreenSizeInfo()
        ) {
            if (!screenSizeInfo.isZeroWidth()) {
                if (!screenSizeInfo.isCompactWidth()) {
                    EnergyAppDesktopEntryPoint()
                }
            }
        }
    }
}