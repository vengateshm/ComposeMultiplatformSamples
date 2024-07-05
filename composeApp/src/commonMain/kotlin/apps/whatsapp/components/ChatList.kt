package apps.whatsapp.components

import LocalWindowSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.whatsapp.model.Chat
import apps.whatsapp.model.WindowType
import apps.whatsapp.model.toUnreadCountText
import apps.whatsapp.repository.ChatRepository
import apps.whatsapp.ui.primaryGreen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ChatList(
    modifier: Modifier = Modifier,
    onSelect: (Chat) -> Unit
) {
    val chats by ChatRepository.getChats().collectAsState(initial = emptyList())

    val localWindowSize = LocalWindowSize.current
    if (localWindowSize.width != WindowType.Compact) {
        LaunchedEffect(Unit) {
            chats.takeIf { it.isNotEmpty() }?.let {
                onSelect(it[0])
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(chats) { chat ->
            ChatItem(
                chat = chat,
                onSelect = {
                    onSelect(it)
                })
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier, chat: Chat,
    onSelect: (Chat) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            onSelect(chat)
        },
        verticalAlignment = Alignment.Top
    ) {
        KamelImage(
            resource = asyncPainterResource(
                data = chat.userImgUrl,
                filterQuality = FilterQuality.High,
            ),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            onLoading = { CircularProgressIndicator(progress = { it }) },
            onFailure = { exception: Throwable ->
                exception.printStackTrace()
            },
        )
        Column(
            modifier = Modifier.weight(weight = 1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = chat.userName,
                color = Color(0XFF09132C),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = chat.lastMessage,
                color = Color(0XFF09132C),
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = chat.dateTime,
                color = Color(0XFF829C99),
                fontSize = 12.sp
            )
            Box(
                modifier = Modifier.size(24.dp)
                    .background(color = Color(0XFFDFF6F4), shape = CircleShape)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = chat.toUnreadCountText(),
                    color = primaryGreen,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}