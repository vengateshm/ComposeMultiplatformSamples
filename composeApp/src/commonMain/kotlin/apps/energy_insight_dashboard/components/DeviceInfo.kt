package apps.energy_insight_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import apps.energy_insight_dashboard.domain.model.DeviceInfo
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.DevicesSectionIconBg
import apps.energy_insight_dashboard.ui.DevicesSectionSelectedBg
import apps.energy_insight_dashboard.ui.DevicesSectionStatusText
import apps.energy_insight_dashboard.ui.DevicesSectionText
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.Size12
import apps.energy_insight_dashboard.ui.Size14
import apps.energy_insight_dashboard.ui.SmallDp
import apps.energy_insight_dashboard.ui.SwitchTrackChecked
import apps.energy_insight_dashboard.ui.SwitchTrackUnChecked
import apps.energy_insight_dashboard.ui.XSmallRoundedCorners
import org.jetbrains.compose.resources.painterResource

@Composable
fun DeviceInfo(
    modifier: Modifier = Modifier,
    shape: Shape,
    deviceInfo: DeviceInfo,
    isMinScreenWidth: Boolean,
    itemWidth: Dp
) {
    var isOn by remember { mutableStateOf(deviceInfo.isOn) }

    Card(
        onClick = {},
        modifier = modifier,
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = SmallDp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .clip(shape)
                .background(
                    color = if (isOn) DevicesSectionSelectedBg else Color.White
                )
                .padding(all = SmallDp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isOn) "On" else "Off",
                    fontSize = Size14,
                    fontFamily = EnergyFontFamily(),
                    color = DevicesSectionStatusText
                )
                Switch(
                    modifier = Modifier.graphicsLayer {
                        scaleX = if (isMinScreenWidth) 0.6f else 1f
                        scaleY = if (isMinScreenWidth) 0.6f else 1f
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = SwitchTrackUnChecked,
                        checkedTrackColor = SwitchTrackChecked
                    ),
                    checked = isOn,
                    onCheckedChange = {
                        isOn = it
                    })
            }
            BoxWithConstraints(
                modifier = Modifier
                    .weight(weight = 1f)
            ) {
                val availableHeight = maxHeight
                Box(
                    modifier = Modifier
                        .size(if (isMinScreenWidth) availableHeight else (availableHeight - availableHeight / 4))
                        .background(
                            color = DevicesSectionIconBg,
                            shape = if (isMinScreenWidth) XSmallRoundedCorners else DashBoardCardShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .graphicsLayer {
                                val scale = if (isMinScreenWidth) 0.6f else 0.9f
                                scaleX = scale
                                scaleY = scale
                            },
                        painter = painterResource(deviceInfo.iconRes),
                        contentDescription = "${deviceInfo.name} icon",
                        tint = Color.White
                    )
                }
            }
            VMediumSpacer()
            Text(
                text = deviceInfo.name,
                fontSize = Size12,
                fontFamily = EnergyFontFamily(),
                color = DevicesSectionText
            )
        }
    }
}