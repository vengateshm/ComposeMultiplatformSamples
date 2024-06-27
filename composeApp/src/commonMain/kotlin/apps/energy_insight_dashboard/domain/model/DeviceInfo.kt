package apps.energy_insight_dashboard.domain.model

import org.jetbrains.compose.resources.DrawableResource

data class DeviceInfo(
    val name: String,
    val iconRes: DrawableResource,
    val isOn: Boolean
)
