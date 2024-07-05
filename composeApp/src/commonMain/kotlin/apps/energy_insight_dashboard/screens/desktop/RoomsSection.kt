package apps.energy_insight_dashboard.screens.desktop

import LocalScreenSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import apps.energy_insight_dashboard.components.RoomInfo
import apps.energy_insight_dashboard.components.SearchBar
import apps.energy_insight_dashboard.components.VSmallSpacer
import apps.energy_insight_dashboard.data.repository.RoomInfoRepositoryImpl
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.SectionTitle
import apps.energy_insight_dashboard.ui.Size15
import apps.energy_insight_dashboard.ui.Size20
import apps.energy_insight_dashboard.ui.SmallDp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoomsSection(modifier: Modifier = Modifier) {
    val data = remember { RoomInfoRepositoryImpl().getRoomInfo() }
    var selectedRoomWithDevices by remember { mutableStateOf(data[0]) }

    val screenWidthInDp = LocalScreenSize.current.wDP
    val isMinScreenWidth = screenWidthInDp <= 800.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "Rooms",
            fontSize = if (isMinScreenWidth) Size15 else Size20,
            fontFamily = EnergyFontFamily(),
            color = SectionTitle
        )
        SearchBar(onSearchQueryChange = {})
    }
    VSmallSpacer()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val availableMaxWidth = maxWidth
        val items = if (isMinScreenWidth) data.take(3) else data
        val padding = SmallDp
        val itemWidth =
            (availableMaxWidth - (items.size - 1) * padding) / items.size // Subtracting padding between cards

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding)
        ) {
            items.forEach {
                RoomInfo(
                    modifier = Modifier.width(width = itemWidth).aspectRatio(1f),
                    isMinScreenWidth = isMinScreenWidth,
                    onClick = {
                        selectedRoomWithDevices = it
                    },
                    shape = DashBoardCardShape,
                    roomInfo = it,
                    isSelected = it == selectedRoomWithDevices,
                )
            }
        }
    }
}