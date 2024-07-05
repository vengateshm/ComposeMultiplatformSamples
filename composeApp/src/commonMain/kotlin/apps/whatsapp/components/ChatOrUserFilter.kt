package apps.whatsapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.whatsapp.ui.color7D
import apps.whatsapp.ui.primaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatOrUserFilter(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableStateOf(0) }
    val options = listOf("Favourites", "Friends", "Groups")
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                icon = {},
                shape = SegmentedButtonDefaults.itemShape(
                    index = index, count = options.size,
                    baseShape = RoundedCornerShape(8.dp)
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = primaryGreen,
                    activeContentColor = Color.White,
                    activeBorderColor = Color.Transparent,
                    inactiveContainerColor = Color.White,
                    inactiveContentColor = color7D,
                    inactiveBorderColor = Color.Transparent,
                )
            ) {
                Text(
                    text = label,
                    fontSize = 14.sp
                )
            }
        }
    }
}