package apps.energy_insight_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import apps.energy_insight_dashboard.domain.model.LevelInfo
import apps.energy_insight_dashboard.domain.model.LevelType
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.LevelNameText
import apps.energy_insight_dashboard.ui.PrimaryText
import apps.energy_insight_dashboard.ui.Size13
import apps.energy_insight_dashboard.ui.Size15
import apps.energy_insight_dashboard.ui.Size16
import apps.energy_insight_dashboard.ui.Size18
import apps.energy_insight_dashboard.ui.SmallDp
import apps.energy_insight_dashboard.ui.XSmallRoundedCorners
import org.jetbrains.compose.resources.painterResource

@Composable
fun LevelInfo(
    modifier: Modifier = Modifier,
    shape: Shape,
    isMinScreenWidth: Boolean,
    levelInfo: LevelInfo
) {
    Card(
        onClick = {},
        modifier = modifier,
        shape = shape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = SmallDp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .clip(shape)
                .background(color = Color.White)
                .padding(all = SmallDp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = levelInfo.iconBgColor,
                            shape = XSmallRoundedCorners
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.graphicsLayer {
                            scaleX = 0.7f
                            scaleY = 0.7f
                        },
                        painter = painterResource(levelInfo.iconRes),
                        contentDescription = "${levelInfo.name} icon",
                        tint = Color.White
                    )
                }
                HMediumSpacer()
                Text(
                    text = levelInfo.name,
                    fontSize = if (isMinScreenWidth) Size15 else Size18,
                    fontFamily = EnergyFontFamily(),
                    color = LevelNameText
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = levelInfo.filter,
                    fontSize = if (isMinScreenWidth) Size13 else Size16,
                    fontFamily = EnergyFontFamily(),
                    color = PrimaryText
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "levels arrow down icon"
                )
            }
            when (levelInfo.type) {
                LevelType.Humidity -> {
                    HumidityLineChart(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                LevelType.Energy -> {
                    EnergyChart(
                        modifier = Modifier.fillMaxSize(),
                        isMinScreenWidth = isMinScreenWidth
                    )
                }
            }
        }
    }
}