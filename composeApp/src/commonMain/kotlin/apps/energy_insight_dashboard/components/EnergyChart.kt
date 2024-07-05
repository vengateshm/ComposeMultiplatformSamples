package apps.energy_insight_dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.energy_insight_dashboard.ui.EnergyPrimaryBlue

@Composable
fun EnergyChart(modifier: Modifier = Modifier, isMinScreenWidth: Boolean) {
    val days = remember { listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat") }
    val values = remember { listOf(30f, 52f, 34f, 60f, 48f, 23f, 67f) }
    val barColor = remember { Color(0xFFE8F3FC) }
    val minBarColor = EnergyPrimaryBlue

    val barSpacing = with(LocalDensity.current) {
        8.dp.toPx()
    }
    val marginBottom = with(LocalDensity.current) {
        24.dp.toPx()
    }// margin at the bottom for text
    val textSize = with(LocalDensity.current) {
        12.sp.toPx()
    }
    val textMarginTop = with(LocalDensity.current) {
        8.dp.toPx()
    }
    val textMeasure = rememberTextMeasurer()

    Canvas(modifier = Modifier.padding(top = 4.dp).fillMaxSize()) {
        val width = size.width
        val height = size.height - marginBottom

        val totalBars = values.size
        val barWidth = (width - ((totalBars - 1) * barSpacing)) / totalBars

        val minValue = values.minOrNull() ?: 0f
        val maxValue = values.maxOrNull() ?: 0f

        for (i in 0..3) {
            val y = i * ((height) / 4)
            drawLine(
                color = Color(0XFFC8C8C8),
                start = Offset(x = 0f, y = y),
                end = Offset(x = width, y = y),
                strokeWidth = 2f,
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }
        values.forEachIndexed { index, value ->
            val barHeight = value / maxValue * height
            val left = index * (barWidth + barSpacing)
            val top = height - barHeight

            drawRoundRect(
                color = if (value == minValue) minBarColor else barColor,
                topLeft = Offset(x = left, y = top),
                size = Size(width = barWidth, height = barHeight),
                cornerRadius = CornerRadius(x = 12f, y = 12f)
            )
            // Jetbrains Skia API
            /*drawContext.canvas.nativeCanvas.drawTextLine(
                TextLine.Companion.make(
                    if (isMinScreenWidth) days[index].first().toString() else days[index],
                    Font(typeface = Typeface.makeDefault(), size = textSize)
                ),
                x = left + (barWidth / 4),
                y = height + marginBottom - textMarginTop,
                Paint().apply {
                    isAntiAlias = true
                    color = 0xFF454444.toInt()
                }
            )*/
            val textLayoutResult = textMeasure.measure(
                text = if (isMinScreenWidth) days[index].first().toString() else days[index],
                style = TextStyle(
                    color = Color(0XFF454444),
                    fontSize = 12.sp
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x = left + (barWidth / 4),
                    y = height + textMarginTop
                )
            )
        }
    }
}