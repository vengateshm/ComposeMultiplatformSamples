package apps.energy_insight_dashboard.data.repository

import apps.energy_insight_dashboard.domain.model.LevelInfo
import apps.energy_insight_dashboard.domain.model.LevelType
import apps.energy_insight_dashboard.domain.repository.LevelInfoRepository
import apps.energy_insight_dashboard.ui.LevelEnergyIconBg
import apps.energy_insight_dashboard.ui.LevelHumidityIconBg
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_energy
import composemultiplatformsamples.composeapp.generated.resources.ic_humidity

class LevelInfoRepositoryImpl : LevelInfoRepository {
    override fun getLevelInfo(): List<LevelInfo> {
        return listOf(
            LevelInfo(
                type = LevelType.Humidity,
                name = "Humidity",
                iconRes = Res.drawable.ic_humidity,
                iconBgColor = LevelHumidityIconBg,
                filter = "Today"
            ),
            LevelInfo(
                type = LevelType.Energy,
                name = "Energy",
                iconRes = Res.drawable.ic_energy,
                iconBgColor = LevelEnergyIconBg,
                filter = "Week"
            ),
        )
    }
}