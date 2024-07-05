package apps.energy_insight_dashboard.screens.desktop

import LocalScreenSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.energy_insight_dashboard.components.HistoryInfo
import apps.energy_insight_dashboard.components.MemberInfo
import apps.energy_insight_dashboard.components.TitleViewAll
import apps.energy_insight_dashboard.components.VMediumSpacer
import apps.energy_insight_dashboard.data.repository.DeviceInfoRepositoryImpl
import apps.energy_insight_dashboard.data.repository.MemberInfoRepositoryImpl
import apps.energy_insight_dashboard.ui.AddMemberText
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.Size13
import apps.energy_insight_dashboard.ui.Size15
import apps.energy_insight_dashboard.ui.SmallDp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MembersAndHistorySection(
    modifier: Modifier = Modifier
) {
    val memberInfos = remember { MemberInfoRepositoryImpl().getMemberInfo() }
    val historyItem = remember { DeviceInfoRepositoryImpl().getDeviceHistoryInfo() }

    val screenWidthInDp = LocalScreenSize.current.wDP
    val isMinScreenWidth = screenWidthInDp <= 800.dp

    Column {
        TitleViewAll(title = "Members", isMinScreenWidth = isMinScreenWidth)
        VMediumSpacer()
        Card(
            onClick = {},
            modifier = Modifier.wrapContentSize(),
            shape = DashBoardCardShape,
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = SmallDp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(all = SmallDp),
                verticalArrangement = Arrangement.spacedBy(SmallDp)
            ) {
                memberInfos.forEach {
                    MemberInfo(memberInfo = it, isMinScreenWidth = isMinScreenWidth)
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFCCDBFD))
                ) {
                    Text(
                        text = "Add Member",
                        color = AddMemberText,
                        fontSize = if (isMinScreenWidth) Size13 else Size15,
                        fontFamily = EnergyFontFamily(),
                    )
                }
            }
        }
    }
    VMediumSpacer()
    Column {
        TitleViewAll(title = "History", isMinScreenWidth = isMinScreenWidth)
        VMediumSpacer()
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            historyItem.forEach {
                HistoryInfo(
                    isMinScreenWidth = isMinScreenWidth,
                    historyItem = it
                )
            }
        }
    }
}