package apps.energy_insight_dashboard.screens.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.energy_insight_dashboard.components.HLargeSpacer
import apps.energy_insight_dashboard.components.HMediumSpacer
import apps.energy_insight_dashboard.components.VMediumSpacer
import apps.energy_insight_dashboard.ui.DividerColor
import apps.energy_insight_dashboard.ui.SmallDp

@Composable
fun EnergyAppDesktopEntryPoint(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0XFFE8F3FC))
            .padding(all = SmallDp)
    ) {
        Column(modifier = Modifier.weight(0.9f)) {
            DashboardMenuSection()
        }
        HMediumSpacer()
        Column(
            modifier = Modifier.weight(2f).fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            RoomsSection()
            VMediumSpacer()
            LevelsSection()
            VMediumSpacer()
            DevicesSection()
        }
        HLargeSpacer()
        Box(modifier = Modifier.width(1.dp).fillMaxHeight().background(color = DividerColor))
        HLargeSpacer()
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DashboardActions(onClick = {})
            VMediumSpacer()
            MembersAndHistorySection()
        }
    }
}