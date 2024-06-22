package apps.whatsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import apps.whatsapp.bgLeftPane
import apps.whatsapp.model.Chat
import apps.whatsapp.primaryGreen
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_chat
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChatsContent(
    widthFraction: Float = 1.0f,
    onSelect: (Chat) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxHeight()
            .fillMaxWidth(fraction = widthFraction)
            .background(color = bgLeftPane)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
        ) {
            UserOrChatSearchBar(onQueryChange = {

            })
            Spacer(modifier = Modifier.height(16.dp))
            ChatOrUserFilter()
            Spacer(modifier = Modifier.height(16.dp))
            ChatList(onSelect = onSelect)
        }
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
            onClick = {},
            shape = CircleShape,
            containerColor = primaryGreen
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_chat), contentDescription = null,
                tint = Color.White
            )
        }
    }
}