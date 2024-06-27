package apps.energy_insight_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import apps.energy_insight_dashboard.domain.model.RoomInfo
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.EnergyPrimaryBlue
import apps.energy_insight_dashboard.ui.Size12
import apps.energy_insight_dashboard.ui.Size14
import apps.energy_insight_dashboard.ui.SmallDp
import apps.energy_insight_dashboard.ui.XSmallRoundedCorners
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoomInfo(
    modifier: Modifier = Modifier,
    isMinScreenWidth: Boolean,
    onClick: () -> Unit,
    shape: Shape,
    roomInfo: RoomInfo,
    isSelected: Boolean
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = SmallDp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .clip(shape)
                .background(
                    brush = if (isSelected) Brush.linearGradient(
                        colors = listOf(
                            Color(0XFF5667FF),
                            Color(0XFF2396EF)
                        )
                    )
                    else Brush.linearGradient(colors = listOf(Color.White, Color.White))
                )
                .padding(all = SmallDp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .weight(weight = 1f)
            ) {
                val availableHeight = maxHeight
                Box(
                    modifier = Modifier
                        .size(availableHeight / 2)
                        .background(
                            color = if (isSelected) Color(0XFF3054AA) else Color(0XFF2396EF).copy(
                                alpha = 0.41f
                            ),
                            shape = if (isMinScreenWidth) XSmallRoundedCorners else DashBoardCardShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .graphicsLayer {
                                val scale = if (isMinScreenWidth) 0.6f else 1f
                                scaleX = scale
                                scaleY = scale
                            },
                        painter = painterResource(roomInfo.iconRes),
                        contentDescription = "${roomInfo.name} icon",
                        tint = if (isSelected) Color.White else EnergyPrimaryBlue
                    )
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = roomInfo.name,
                    fontSize = Size14,
                    fontFamily = EnergyFontFamily(),
                    color = if (isSelected) Color(0XFFFFFFFF) else Color(0XFF1D1D1D)
                )
                VMediumSpacer()
                Text(
                    text = "${roomInfo.deviceCount} Devices",
                    fontSize = Size12,
                    fontFamily = EnergyFontFamily(),
                    color = if (isSelected) Color(0XFFFFFFFF) else Color(0XFF1D1D1D)
                )
            }
        }
    }
}