package apps.energy_insight_dashboard.domain.repository

import apps.energy_insight_dashboard.domain.model.LevelInfo

interface LevelInfoRepository {
    fun getLevelInfo(): List<LevelInfo>
}