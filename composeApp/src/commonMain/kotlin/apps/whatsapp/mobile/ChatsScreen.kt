package apps.whatsapp.mobile

import androidx.compose.runtime.Composable
import apps.whatsapp.components.ChatsContent
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class ChatsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        ChatsContent(
            widthFraction = 1f,
            onSelect = {
                navigator.push(ChatDetailScreen(chat = it))
            })
    }
}