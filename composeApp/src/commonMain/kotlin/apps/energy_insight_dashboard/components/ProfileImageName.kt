package apps.energy_insight_dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.PrimaryText
import apps.energy_insight_dashboard.ui.Size12
import apps.energy_insight_dashboard.ui.Size13
import apps.energy_insight_dashboard.ui.Size16
import apps.energy_insight_dashboard.ui.Size17
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class UserProfile(
    val name: String,
    val profilePicRes: DrawableResource,
)

@Composable
fun ProfileImageName(
    modifier: Modifier = Modifier,
    userProfile: UserProfile,
    isMinimumWidth: Boolean
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .aspectRatio(1f),
            painter = painterResource(userProfile.profilePicRes),
            contentDescription = "${userProfile.name} image"
        )
        HSmallSpacer()
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Hello👋",
                fontSize = if (isMinimumWidth) Size12 else Size16,
                fontFamily = EnergyFontFamily(),
                color = PrimaryText
            )
            Text(
                text = userProfile.name,
                fontSize = if (isMinimumWidth) Size13 else Size17,
                fontFamily = EnergyFontFamily(),
                color = PrimaryText
            )
        }
    }
}