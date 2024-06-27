package apps.energy_insight_dashboard.data.repository

import apps.energy_insight_dashboard.domain.model.DeviceInfo
import apps.energy_insight_dashboard.domain.model.HistoryInfo
import apps.energy_insight_dashboard.domain.repository.DeviceInfoRepository
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_air_conditioner
import composemultiplatformsamples.composeapp.generated.resources.ic_coffee_machine
import composemultiplatformsamples.composeapp.generated.resources.ic_refrigerator
import composemultiplatformsamples.composeapp.generated.resources.ic_smart_tv

class DeviceInfoRepositoryImpl : DeviceInfoRepository {
    override fun getDeviceInfo(): List<DeviceInfo> {
        return listOf(
            DeviceInfo(
                name = "Air Conditioner",
                isOn = false,
                iconRes = Res.drawable.ic_air_conditioner
            ),
            DeviceInfo(
                name = "Smart TV",
                isOn = true,
                iconRes = Res.drawable.ic_smart_tv
            ),
            DeviceInfo(
                name = "Coffee Machine",
                isOn = false,
                iconRes = Res.drawable.ic_coffee_machine
            ),
            DeviceInfo(
                name = "Refrigerator",
                isOn = true,
                iconRes = Res.drawable.ic_refrigerator
            ),
        )
    }

    override fun getDeviceHistoryInfo(): List<HistoryInfo> {
        return listOf(
            HistoryInfo(
                deviceName = "Air conditioner",
                status = "Turned on",
                dateTime = "03:20",
                userName = "Jacquiline"
            ),
            HistoryInfo(
                deviceName = "Refrigerator",
                status = "Turned on",
                dateTime = "01:48",
                userName = "Firoz"
            ),
            HistoryInfo(
                deviceName = "Air conditioner",
                status = "Turned off",
                dateTime = "11:36",
                userName = "Jacquiline"
            ),
            HistoryInfo(
                deviceName = "Coffee Machine",
                status = "Turned off",
                dateTime = "09:15",
                userName = "Jacquiline"
            )
        )
    }
}