package apps.whatsapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import apps.whatsapp.model.Chat

@Composable
fun ChatDetailContent(modifier: Modifier = Modifier, chat: Chat?) {
    Column(modifier = Modifier.fillMaxSize()) {
        chat?.run {
            ChatDetailHeader(chat = this)
            ChatDetailBody(chat = this)
            ChatDetailFooter(onQueryChange = {})
        }
    }
}