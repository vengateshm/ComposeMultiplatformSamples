package apps.energy_insight_dashboard.domain.repository

import apps.energy_insight_dashboard.domain.model.DeviceInfo
import apps.energy_insight_dashboard.domain.model.HistoryInfo

interface DeviceInfoRepository {
    fun getDeviceInfo(): List<DeviceInfo>
    fun getDeviceHistoryInfo(): List<HistoryInfo>
}