package apps.energy_insight_dashboard.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import apps.energy_insight_dashboard.components.DeviceInfo
import apps.energy_insight_dashboard.components.TitleArrowRight
import apps.energy_insight_dashboard.components.VSmallSpacer
import apps.energy_insight_dashboard.data.repository.DeviceInfoRepositoryImpl
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.SmallDp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DevicesSection(modifier: Modifier = Modifier) {
    val data = remember { DeviceInfoRepositoryImpl().getDeviceInfo() }

    val screenWidthInDp = with(LocalDensity.current) {
        LocalWindowInfo.current.containerSize.width.toDp()
    }
    val isMinScreenWidth = screenWidthInDp <= 800.dp

    TitleArrowRight(
        title = "Devices",
        isMinScreenWidth = isMinScreenWidth
    )
    VSmallSpacer()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val availableMaxWidth = maxWidth
        val items = if (isMinScreenWidth) data.take(3) else data
        val padding = SmallDp
        val itemWidth =
            (availableMaxWidth - (items.size - 1) * padding) / items.size // Subtracting padding between cards

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding)
        ) {
            items.forEach {
                DeviceInfo(
                    modifier = Modifier.width(width = itemWidth).aspectRatio(1f),
                    isMinScreenWidth = isMinScreenWidth,
                    itemWidth = itemWidth,
                    shape = DashBoardCardShape,
                    deviceInfo = it
                )
            }
        }
    }
}