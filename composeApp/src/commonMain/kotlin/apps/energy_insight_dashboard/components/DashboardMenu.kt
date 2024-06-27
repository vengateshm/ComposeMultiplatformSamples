package apps.energy_insight_dashboard.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import apps.energy_insight_dashboard.domain.model.DashboardMenu
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.MenuItemText
import apps.energy_insight_dashboard.ui.Size14
import apps.energy_insight_dashboard.ui.Size18
import org.jetbrains.compose.resources.painterResource

@Composable
fun DashboardMenu(
    modifier: Modifier = Modifier,
    dashboardMenu: DashboardMenu,
    isMinScreenWidth: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(dashboardMenu.iconRes),
            contentDescription = "${dashboardMenu.name} icon"
        )
        HMediumSpacer()
        Text(
            text = dashboardMenu.name,
            fontFamily = EnergyFontFamily(),
            fontSize = if (isMinScreenWidth) Size14 else Size18,
            color = MenuItemText
        )
    }
}