package samples.async_image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AsyncImageSample(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.size(150.dp),
            url = "https://plus.unsplash.com/premium_photo-1683910767532-3a25b821f7ae?q=80&w=2608&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        )
        AsyncImage(
            modifier = Modifier.size(150.dp),
            url = "https://plus.unsplash.com/premium_photo-1683910767532-3a25b821f7ae?q=80&w=2608&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            imageTransformation = ImageTransformation.Circle
        )
        AsyncImage(
            modifier = Modifier.size(150.dp),
            url = "https://plus.unsplash.com/premium_photo-1683910767532-3a25b821f7ae?q=80&w=2608&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            imageTransformation = ImageTransformation.RoundedCorner.apply { radius = 24 }
        )
    }
}