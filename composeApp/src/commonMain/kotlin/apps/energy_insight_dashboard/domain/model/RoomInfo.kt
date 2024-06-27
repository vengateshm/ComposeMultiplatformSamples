package apps.energy_insight_dashboard.domain.model

import org.jetbrains.compose.resources.DrawableResource

data class RoomInfo(
    val name: String,
    val deviceCount: Int,
    val iconRes: DrawableResource
)
