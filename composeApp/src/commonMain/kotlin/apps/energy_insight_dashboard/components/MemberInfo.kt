package apps.energy_insight_dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import apps.energy_insight_dashboard.domain.model.MemberInfo
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.PrimaryText
import apps.energy_insight_dashboard.ui.Size12
import apps.energy_insight_dashboard.ui.Size14
import apps.energy_insight_dashboard.ui.Size16
import apps.energy_insight_dashboard.ui.Size18
import org.jetbrains.compose.resources.painterResource

@Composable
fun MemberInfo(
    modifier: Modifier = Modifier,
    memberInfo: MemberInfo,
    isMinScreenWidth: Boolean
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .aspectRatio(1f),
            painter = painterResource(memberInfo.profilePicRes),
            contentDescription = "${memberInfo.name} image"
        )
        HSmallSpacer()
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = memberInfo.name,
                fontSize = if (isMinScreenWidth) Size14 else Size18,
                fontFamily = EnergyFontFamily(),
                color = PrimaryText
            )
            Text(
                text = memberInfo.access,
                fontSize = if (isMinScreenWidth) Size12 else Size16,
                fontFamily = EnergyFontFamily(),
                color = PrimaryText.copy(alpha = 0.5f)
            )
        }
    }
}