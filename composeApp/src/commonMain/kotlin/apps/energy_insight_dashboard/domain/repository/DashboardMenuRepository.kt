package apps.energy_insight_dashboard.domain.repository

import apps.energy_insight_dashboard.domain.model.DashboardMenu

interface DashboardMenuRepository {
    fun getDashboardMenu(): List<DashboardMenu>
}