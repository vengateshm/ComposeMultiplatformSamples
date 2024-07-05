package samples.async_image

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import java.io.ByteArrayInputStream

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = 1f,
    colorFilter: ColorFilter? = null,
    imageTransformation: ImageTransformation = ImageTransformation.Rectangle
) {
    var image: ImageBitmap? by remember { mutableStateOf(null) }
    produceState(image) {
        if (this.value == null) {
            try {
                value = HttpClient(CIO).use {
                    ByteArrayInputStream(it.get(url).readBytes())
                }.use(::loadImageBitmap).also { image = it }
            } catch (e: Exception) {
                println(e.cause?.message)
            }
        }
    }

    AnimatedVisibility(
        visible = image != null,
        enter = fadeIn(animationSpec = tween(750)),
        exit = fadeOut(animationSpec = tween(750))
    ) {
        Image(
            modifier = modifier.clip(
                shape = when (imageTransformation) {
                    ImageTransformation.Circle -> CircleShape
                    ImageTransformation.Rectangle -> RectangleShape
                    ImageTransformation.RoundedCorner -> RoundedCornerShape(imageTransformation.radius)
                }
            ),
            bitmap = image!!,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            colorFilter = colorFilter,
            alpha = alpha
        )
    }
}

enum class ImageTransformation(var radius: Int = 0) {
    Circle,
    Rectangle,
    RoundedCorner(radius = 8)
}