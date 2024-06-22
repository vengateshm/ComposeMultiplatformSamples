package apps.whatsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource

val searchBarContentColor = Color(0XFF9393C1)

@Composable
fun UserOrChatSearchBar(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = {
            query = it
            onQueryChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(25.dp)),
        placeholder = { Text("Search or start a new chat") },
        leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_search), contentDescription = null,
                    tint = searchBarContentColor
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = searchBarContentColor,
            unfocusedTextColor = searchBarContentColor,
            focusedPlaceholderColor = searchBarContentColor,
            unfocusedPlaceholderColor = searchBarContentColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),
        shape = RoundedCornerShape(25.dp)
    )
}