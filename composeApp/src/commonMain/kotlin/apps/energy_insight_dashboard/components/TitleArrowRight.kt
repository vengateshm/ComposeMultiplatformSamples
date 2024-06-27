package apps.energy_insight_dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.EnergyPrimaryBlue
import apps.energy_insight_dashboard.ui.SectionTitle
import apps.energy_insight_dashboard.ui.Size15
import apps.energy_insight_dashboard.ui.Size20

@Composable
fun TitleArrowRight(
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
            color = SectionTitle
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "arrow right icon",
            tint = EnergyPrimaryBlue
        )
    }
}