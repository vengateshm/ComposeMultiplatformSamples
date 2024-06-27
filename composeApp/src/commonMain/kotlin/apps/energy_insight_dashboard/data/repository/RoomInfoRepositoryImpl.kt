package apps.energy_insight_dashboard.data.repository

import apps.energy_insight_dashboard.domain.model.RoomInfo
import apps.energy_insight_dashboard.domain.repository.RoomInfoRepository
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_bathroom
import composemultiplatformsamples.composeapp.generated.resources.ic_bed
import composemultiplatformsamples.composeapp.generated.resources.ic_kitchen
import composemultiplatformsamples.composeapp.generated.resources.ic_living_room

class RoomInfoRepositoryImpl : RoomInfoRepository {
    override fun getRoomInfo(): List<RoomInfo> {
        return listOf(
            RoomInfo(
                name = "Kitchen",
                deviceCount = 8,
                iconRes = Res.drawable.ic_kitchen
            ),
            RoomInfo(
                name = "Living Room",
                deviceCount = 12,
                iconRes = Res.drawable.ic_living_room
            ),
            RoomInfo(
                name = "Bedroom",
                deviceCount = 4,
                iconRes = Res.drawable.ic_bed
            ),
            RoomInfo(
                name = "Bathroom",
                deviceCount = 3,
                iconRes = Res.drawable.ic_bathroom
            ),
        )
    }
}