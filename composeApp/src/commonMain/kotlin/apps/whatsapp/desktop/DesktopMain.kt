package apps.whatsapp.desktop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import apps.whatsapp.components.ChatDetailContent
import apps.whatsapp.components.ChatsContent
import apps.whatsapp.model.Chat

@Composable
fun DesktopMain(modifier: Modifier = Modifier) {
    Row(modifier = Modifier.fillMaxWidth()) {
        var selectedChat by remember { mutableStateOf<Chat?>(null) }

        ChatsContent(
            widthFraction = 0.4f,
            onSelect = {
                selectedChat = it
            })
        Box(modifier = Modifier.weight(weight = 1f)) {
            ChatDetailContent(chat = selectedChat)
        }
    }
}

