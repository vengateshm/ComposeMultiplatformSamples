package apps.energy_insight_dashboard.domain.model

import org.jetbrains.compose.resources.DrawableResource

data class MemberInfo(
    val name: String,
    val access: String,
    val profilePicRes: DrawableResource
)
