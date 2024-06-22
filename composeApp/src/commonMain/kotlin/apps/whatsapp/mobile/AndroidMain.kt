package apps.whatsapp.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun AndroidMain(modifier: Modifier = Modifier) {
    Navigator(ChatsScreen())
}