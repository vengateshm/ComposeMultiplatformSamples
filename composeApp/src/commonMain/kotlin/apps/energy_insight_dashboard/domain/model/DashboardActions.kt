package apps.energy_insight_dashboard.domain.model

import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_faq
import composemultiplatformsamples.composeapp.generated.resources.ic_notification
import org.jetbrains.compose.resources.DrawableResource

enum class DashboardActionControlType {
    Button,
    Switch
}

enum class DashboardActions(val iconRes: DrawableResource, val type: DashboardActionControlType) {
    THEME(Res.drawable.ic_notification, DashboardActionControlType.Switch),
    NOTIFICATION(Res.drawable.ic_faq, DashboardActionControlType.Button),
    FAQ(Res.drawable.ic_faq, DashboardActionControlType.Button)
}