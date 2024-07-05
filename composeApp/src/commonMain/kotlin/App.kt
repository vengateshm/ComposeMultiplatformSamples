import androidx.compose.runtime.Composable
import apps.energy_insight_dashboard.EnergyAppEntryPoint
import apps.whatsapp.model.WindowSize

@Composable
fun App(windowSize: WindowSize? = null) {
    EnergyAppEntryPoint()
}