package apps.energy_insight_dashboard.screens.desktop

import LocalScreenSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.energy_insight_dashboard.components.DashboardMenu
import apps.energy_insight_dashboard.components.ProfileImageName
import apps.energy_insight_dashboard.components.UserProfile
import apps.energy_insight_dashboard.components.VLargeSpacer
import apps.energy_insight_dashboard.components.VSmallSpacer
import apps.energy_insight_dashboard.data.repository.DashboardMenuRepositoryImpl
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.MenuHeaderText
import apps.energy_insight_dashboard.ui.Size14
import apps.energy_insight_dashboard.ui.Size18
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_profile_pic

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DashboardMenuSection(
    modifier: Modifier = Modifier
) {
    val repository = remember { DashboardMenuRepositoryImpl() }
    val mainMenu = remember { repository.getDashboardMenu().take(4) }
    val settingsMenu = remember { repository.getDashboardMenu().takeLast(4) }

    val screenWidthInDp = LocalScreenSize.current.wDP
    val isMinScreenWidth = screenWidthInDp <= 800.dp

    val userProfile = remember {
        UserProfile(
            name = "Jenifer Feroz",
            profilePicRes = Res.drawable.ic_profile_pic
        )
    }

    Card(
        onClick = {},
        modifier = Modifier.fillMaxHeight(),
        shape = DashBoardCardShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProfileImageName(
                userProfile = userProfile,
                isMinimumWidth = isMinScreenWidth

            )
            VLargeSpacer()
            Text(
                text = "Menu",
                fontSize = if (isMinScreenWidth) Size14 else Size18,
                fontFamily = EnergyFontFamily(),
                color = MenuHeaderText
            )
            VLargeSpacer()
            mainMenu.forEach {
                VSmallSpacer()
                DashboardMenu(dashboardMenu = it, isMinScreenWidth = isMinScreenWidth)
            }
            VLargeSpacer()
            Text(
                text = "Settings",
                fontSize = if (isMinScreenWidth) Size14 else Size18,
                fontFamily = EnergyFontFamily(),
                color = MenuHeaderText
            )
            VLargeSpacer()
            settingsMenu.forEach {
                VSmallSpacer()
                DashboardMenu(dashboardMenu = it, isMinScreenWidth = isMinScreenWidth)
            }
        }
    }
}