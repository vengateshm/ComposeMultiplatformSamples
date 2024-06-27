package apps.energy_insight_dashboard.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import apps.energy_insight_dashboard.ui.LargeDp
import apps.energy_insight_dashboard.ui.MediumDp
import apps.energy_insight_dashboard.ui.SmallDp
import apps.energy_insight_dashboard.ui.XSmallDp

// Width Spacer
@Composable
fun HXSmallSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(width = XSmallDp))
}

@Composable
fun HSmallSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(width = SmallDp))
}

@Composable
fun HMediumSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(width = MediumDp))
}

@Composable
fun HLargeSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(width = LargeDp))
}

// Height Spacer
@Composable
fun VXSmallSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(height = XSmallDp))
}

@Composable
fun VSmallSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(height = SmallDp))
}

@Composable
fun VMediumSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(height = MediumDp))
}

@Composable
fun VLargeSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(height = LargeDp))
}