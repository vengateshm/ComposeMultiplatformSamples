package apps.energy_insight_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.energy_insight_dashboard.domain.model.HistoryInfo
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.Size12
import apps.energy_insight_dashboard.ui.Size13
import apps.energy_insight_dashboard.ui.Size14
import apps.energy_insight_dashboard.ui.Size15
import apps.energy_insight_dashboard.ui.TurnedOffDot
import apps.energy_insight_dashboard.ui.TurnedOnDot

@Composable
fun HistoryInfo(
    modifier: Modifier = Modifier,
    historyItem: HistoryInfo,
    isMinScreenWidth: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .width(8.dp)
                .height(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.size(8.dp).clip(CircleShape)
                    .background(
                        color = if (historyItem.status.equals(
                                "Turned on",
                                ignoreCase = true
                            )
                        ) TurnedOnDot else TurnedOffDot
                    ).padding(top = 4.dp),
            )
        }
        HSmallSpacer()
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(
                text = historyItem.deviceName,
                fontSize = if (isMinScreenWidth) Size13 else Size15,
                fontFamily = EnergyFontFamily(),
                color = Color.Black
            )
            VXSmallSpacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = historyItem.status,
                    fontSize = if (isMinScreenWidth) Size12 else Size15,
                    fontFamily = EnergyFontFamily(),
                    color = Color.Black.copy(alpha = 0.75f)
                )
                HXSmallSpacer()
                Box(
                    modifier = Modifier.size(4.dp)
                        .clip(CircleShape)
                        .background(
                            color = Color.Black.copy(alpha = 0.35f)
                        ),
                )
                HXSmallSpacer()
                Text(
                    text = historyItem.userName,
                    fontSize = if (isMinScreenWidth) Size12 else Size14,
                    fontFamily = EnergyFontFamily(),
                    color = Color.Black.copy(alpha = 0.35f)
                )
            }
        }
        Text(
            text = historyItem.dateTime,
            fontSize = if (isMinScreenWidth) Size13 else Size15,
            fontFamily = EnergyFontFamily(),
            color = Color.Black
        )
    }
}