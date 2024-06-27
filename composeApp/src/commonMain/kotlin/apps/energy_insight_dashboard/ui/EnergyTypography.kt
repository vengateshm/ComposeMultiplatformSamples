package apps.energy_insight_dashboard.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.chivo_bold
import composemultiplatformsamples.composeapp.generated.resources.chivo_medium
import composemultiplatformsamples.composeapp.generated.resources.chivo_regular
import org.jetbrains.compose.resources.Font

@Composable
fun EnergyFontFamily() = FontFamily(
    Font(resource = Res.font.chivo_regular, FontWeight.Normal),
    Font(resource = Res.font.chivo_bold, FontWeight.Bold),
    Font(resource = Res.font.chivo_medium, FontWeight.Medium)
)