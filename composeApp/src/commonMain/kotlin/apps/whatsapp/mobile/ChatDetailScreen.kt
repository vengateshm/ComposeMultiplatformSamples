package apps.whatsapp.mobile

import androidx.compose.runtime.Composable
import apps.whatsapp.components.ChatDetailContent
import apps.whatsapp.model.Chat
import cafe.adriel.voyager.core.screen.Screen

data class ChatDetailScreen(val chat: Chat) : Screen {
    @Composable
    override fun Content() {
        ChatDetailContent(chat = chat)
    }
}
