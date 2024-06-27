package apps.whatsapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.whatsapp.bgChatDetailTop
import apps.whatsapp.model.Chat
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.ic_arrow_down
import composemultiplatformsamples.composeapp.generated.resources.ic_calling
import composemultiplatformsamples.composeapp.generated.resources.ic_search_chat_detail
import composemultiplatformsamples.composeapp.generated.resources.ic_video
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChatDetailHeader(modifier: Modifier = Modifier, chat: Chat) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = bgChatDetailTop),
        verticalAlignment = Alignment.CenterVertically
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
            if (chat.isOnline) {
                Text(
                    text = "Online",
                    color = Color(0XFF6E7FA9),
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
        }
        Icon(painter = painterResource(Res.drawable.ic_calling), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(painter = painterResource(Res.drawable.ic_video), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(Res.drawable.ic_search_chat_detail),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Icon(painter = painterResource(Res.drawable.ic_arrow_down), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
    }
}