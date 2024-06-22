package apps.whatsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.whatsapp.bgChatDetailCenter
import apps.whatsapp.model.Chat
import apps.whatsapp.model.Message
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_lock
import org.jetbrains.compose.resources.painterResource

@Composable
fun ColumnScope.ChatDetailBody(modifier: Modifier = Modifier, chat: Chat) {
    Column(
        modifier = Modifier.weight(weight = 1f).fillMaxWidth()
            .background(color = bgChatDetailCenter)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            reverseLayout = true
        ) {
            items(chat.messages) { message ->
                ChatBubble(message)
                Spacer(modifier = Modifier.height(height = 16.dp))
            }
            item {
                MessageBubble()
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .align(if (message.isCurrentUser) Alignment.End else Alignment.Start)
                .background(
                    color = if (message.isCurrentUser) Color(0XFFD7F8F4) else Color.White,
                    shape = if (message.isCurrentUser) RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 0.dp
                    ) else RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 16.dp
                    )
                )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
                text = message.message,
                color = Color(0XFF010101),
                fontSize = 14.sp
            )
        }
        Text(
            modifier = if (message.isCurrentUser) Modifier.align(Alignment.End) else Modifier.align(
                Alignment.Start
            ),
            text = message.dateTime,
            color = Color(0XFF0B3048),
            fontSize = 12.sp
        )
    }
}

@Composable
fun MessageBubble(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(
                color = Color(0XFFFEECDC),
                shape = RoundedCornerShape(16.dp)
            )
        ) {
            Spacer(modifier = Modifier.width(width = 16.dp))
            Icon(painter = painterResource(Res.drawable.ic_lock), contentDescription = null)
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = "Messages are end-to-end encrypted. No one outside of this chat, \nnot even WhatsApp can read or listen to them click to learn more.",
                color = Color(0XFF312E40),
                fontSize = 12.sp
            )
        }
    }
}