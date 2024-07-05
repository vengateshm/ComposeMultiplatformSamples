package apps.whatsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.whatsapp.ui.bgChatDetailBottom
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_plus
import composemultiplatformsamples.composeapp.generated.resources.ic_voice
import org.jetbrains.compose.resources.painterResource

val chatSearchBarTextColor = Color(0XFF8F8F8F)

@Composable
fun ChatDetailFooter(
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = bgChatDetailBottom)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var query by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.width(16.dp))
        Icon(painter = painterResource(Res.drawable.ic_plus), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        TextField(
            value = query,
            onValueChange = {
                query = it
                onQueryChange(it)
            },
            modifier = modifier
                .weight(weight = 1f)
                .background(color = Color.White, shape = RoundedCornerShape(25.dp)),
            placeholder = { Text("Say Something...") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = chatSearchBarTextColor,
                unfocusedTextColor = chatSearchBarTextColor,
                focusedPlaceholderColor = chatSearchBarTextColor,
                unfocusedPlaceholderColor = chatSearchBarTextColor,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
            ),
            shape = RoundedCornerShape(25.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(painter = painterResource(Res.drawable.ic_voice), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
    }
}