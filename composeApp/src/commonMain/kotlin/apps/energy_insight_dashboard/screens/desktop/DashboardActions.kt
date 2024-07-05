package apps.energy_insight_dashboard.screens.desktop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import apps.energy_insight_dashboard.components.HSmallSpacer
import apps.energy_insight_dashboard.domain.model.DashboardActionControlType
import apps.energy_insight_dashboard.domain.model.DashboardActions
import org.jetbrains.compose.resources.painterResource

@Composable
fun DashboardActions(
    modifier: Modifier = Modifier,
    onClick: (DashboardActions) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DashboardActions.entries.forEach {
            if (it.type == DashboardActionControlType.Switch) {
                var isChecked by remember { mutableStateOf(false) }
                Switch(
                    modifier = Modifier.graphicsLayer {
                        scaleX = 0.7f
                        scaleY = 0.7f
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0XFFFD6F5F),
                        checkedTrackColor = Color.LightGray
                    ),
                    checked = isChecked,
                    onCheckedChange = { isCheck ->
                        isChecked = isCheck
                    })
            } else {
                Icon(
                    modifier = Modifier.size(size = 24.dp),
                    painter = painterResource(it.iconRes),
                    contentDescription = "${it.name} icon"
                )
            }
            HSmallSpacer()
        }
    }
}