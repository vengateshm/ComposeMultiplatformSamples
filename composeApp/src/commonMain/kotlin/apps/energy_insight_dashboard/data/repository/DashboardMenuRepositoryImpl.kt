package apps.energy_insight_dashboard.data.repository

import apps.energy_insight_dashboard.domain.model.DashboardMenu
import apps.energy_insight_dashboard.domain.repository.DashboardMenuRepository
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_contact_us
import composemultiplatformsamples.composeapp.generated.resources.ic_faqs
import composemultiplatformsamples.composeapp.generated.resources.ic_home
import composemultiplatformsamples.composeapp.generated.resources.ic_insights
import composemultiplatformsamples.composeapp.generated.resources.ic_logout
import composemultiplatformsamples.composeapp.generated.resources.ic_message
import composemultiplatformsamples.composeapp.generated.resources.ic_profile
import composemultiplatformsamples.composeapp.generated.resources.ic_recommends

class DashboardMenuRepositoryImpl : DashboardMenuRepository {
    override fun getDashboardMenu(): List<DashboardMenu> {
        return listOf(
            DashboardMenu(
                name = "Home",
                iconRes = Res.drawable.ic_home
            ),
            DashboardMenu(
                name = "Insights",
                iconRes = Res.drawable.ic_insights
            ),
            DashboardMenu(
                name = "Recommends",
                iconRes = Res.drawable.ic_recommends
            ),
            DashboardMenu(
                name = "Messages(2)",
                iconRes = Res.drawable.ic_message
            ),
            DashboardMenu(
                name = "Profile",
                iconRes = Res.drawable.ic_profile
            ),
            DashboardMenu(
                name = "FAQ's",
                iconRes = Res.drawable.ic_faqs
            ),
            DashboardMenu(
                name = "Contact us",
                iconRes = Res.drawable.ic_contact_us
            ),
            DashboardMenu(
                name = "Logout",
                iconRes = Res.drawable.ic_logout
            ),
        )
    }
}