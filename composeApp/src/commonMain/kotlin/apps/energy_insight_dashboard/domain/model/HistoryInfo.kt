package apps.energy_insight_dashboard.domain.model

data class HistoryInfo(
    val deviceName: String,
    val status: String,
    val dateTime: String,
    val userName: String
)
