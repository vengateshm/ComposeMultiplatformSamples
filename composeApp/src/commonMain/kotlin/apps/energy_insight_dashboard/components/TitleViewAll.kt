package apps.energy_insight_dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.Size15
import apps.energy_insight_dashboard.ui.Size20
import apps.energy_insight_dashboard.ui.ViewAllText

@Composable
fun TitleViewAll(
    modifier: Modifier = Modifier,
    title: String,
    isMinScreenWidth: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = if (isMinScreenWidth) Size15 else Size20,
            fontFamily = EnergyFontFamily(),
            color = Color.Black
        )
        Text(
            text = "View all",
            fontSize = if (isMinScreenWidth) Size15 else Size20,
            fontFamily = EnergyFontFamily(),
            color = ViewAllText
        )
    }
}