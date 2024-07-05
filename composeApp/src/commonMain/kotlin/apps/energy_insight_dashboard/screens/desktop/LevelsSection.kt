package apps.energy_insight_dashboard.screens.desktop

import LocalScreenSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import apps.energy_insight_dashboard.components.LevelInfo
import apps.energy_insight_dashboard.components.TitleArrowRight
import apps.energy_insight_dashboard.components.VSmallSpacer
import apps.energy_insight_dashboard.data.repository.LevelInfoRepositoryImpl
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.SmallDp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LevelsSection(modifier: Modifier = Modifier) {
    val levelsInfo = remember { LevelInfoRepositoryImpl().getLevelInfo() }

    val screenWidthInDp = LocalScreenSize.current.wDP
    val isMinScreenWidth = screenWidthInDp <= 800.dp

    TitleArrowRight(title = "Levels", isMinScreenWidth = isMinScreenWidth)
    VSmallSpacer()
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val availableMaxWidth = maxWidth
        val items = levelsInfo
        val padding = SmallDp
        val itemWidth =
            (availableMaxWidth - (items.size - 1) * padding) / items.size // Subtracting padding between cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(padding)
        ) {
            items.forEach {
                LevelInfo(
                    modifier = Modifier.width(width = itemWidth).aspectRatio(1.5f),
                    isMinScreenWidth = isMinScreenWidth,
                    shape = DashBoardCardShape,
                    levelInfo = it
                )
            }
        }
    }
}