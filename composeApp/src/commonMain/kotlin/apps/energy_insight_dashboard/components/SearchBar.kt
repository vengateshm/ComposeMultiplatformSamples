package apps.energy_insight_dashboard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import apps.energy_insight_dashboard.ui.DashBoardCardShape
import apps.energy_insight_dashboard.ui.EnergyFontFamily
import apps.energy_insight_dashboard.ui.SmallDp
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_energy_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchQueryChange: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Card(
        modifier = modifier.fillMaxWidth(fraction = 0.6f),
        shape = DashBoardCardShape,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = SmallDp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        TextField(
            textStyle = TextStyle(
                fontFamily = EnergyFontFamily(),
            ),
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = {
                searchText = it
                onSearchQueryChange(it)
            },
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search Here...",
                    fontFamily = EnergyFontFamily(),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_energy_search),
                    contentDescription = "search icon"
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
        )
    }
}