package apps.energy_insight_dashboard.domain.repository

import apps.energy_insight_dashboard.domain.model.RoomInfo

interface RoomInfoRepository {
    fun getRoomInfo(): List<RoomInfo>
}