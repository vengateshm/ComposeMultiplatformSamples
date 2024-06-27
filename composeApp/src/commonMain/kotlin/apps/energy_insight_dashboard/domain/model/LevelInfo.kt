package apps.energy_insight_dashboard.domain.model

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class LevelInfo(
    val type: LevelType,
    val name: String,
    val iconRes: DrawableResource,
    val iconBgColor: Color,
    val data: Any? = null,
    val filter: String
)
