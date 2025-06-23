package samples.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.capriola_regular
import org.jetbrains.compose.resources.Font

val Background = Color(color = 0xFF58A1F8)
val Primary = Color(color = 0xFF5977F7)
val Surface = Color(color = 0xFFFEFEFF2)
val OnSurface = Color(color = 0xFF1B1B1C)
val OnSurfaceVariant = Color(color = 0xFF535364)
val SurfaceLowest = Color(color = 0xFFFFFFFF)

val LightColorScheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    surfaceContainerLowest = SurfaceLowest,
    background = Background,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant
)

val CapriolaFamily
    @Composable get() = FontFamily(
        Font(
            resource = Res.font.capriola_regular,
            weight = FontWeight.Normal,
        )
    )

val CustomTypography
    @Composable get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = CapriolaFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = CapriolaFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),
        bodySmall = TextStyle(
            fontFamily = CapriolaFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 20.sp
        ),
        titleLarge = TextStyle(
            fontFamily = CapriolaFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 36.sp
        ),
        titleSmall = TextStyle(
            fontFamily = CapriolaFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
    )

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(15.dp),
)

@Composable
fun extraColor(light: Color, dark: Color): Color {
    return if (isSystemInDarkTheme()) dark else light
}

val ColorScheme.extraColor @Composable get() = extraColor(light = Color.White, dark = Color.Black)

@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content,
        typography = CustomTypography,
        shapes = Shapes
    )
}