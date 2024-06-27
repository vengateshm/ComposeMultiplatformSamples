package apps.energy_insight_dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.energy_insight_dashboard.ui.EnergyPrimaryBlue
import org.jetbrains.skia.Font
import org.jetbrains.skia.Paint
import org.jetbrains.skia.TextLine
import org.jetbrains.skia.Typeface

@Composable
fun HumidityLineChart(modifier: Modifier = Modifier) {
    val points = remember {
        listOf(
            Pair(19f, 121f),
            Pair(41f, 139f),
            Pair(96f, 121f),
            Pair(152f, 141f),
            Pair(208f, 115f),
            Pair(264f, 139f),
            Pair(320f, 105f),
        )
    }

    val texts = remember {
        listOf(
            0, 24, 33, 29, 35, 24, 40
        )
    }
    val marginBottom = with(LocalDensity.current) {
        24.dp.toPx()
    }// margin at the bottom for text
    val textMarginTop = with(LocalDensity.current) {
        4.dp.toPx()
    }
    val textSize = with(LocalDensity.current) {
        14.sp.toPx()
    }
    Canvas(modifier = Modifier.padding(top = 4.dp).fillMaxSize()) {

        val scaledPoints = scalePoints(points, size.width, size.height - marginBottom)

        for (i in scaledPoints.indices) {
            if (i > 0) {
                val minY = scaledPoints.takeLast(scaledPoints.size - 1).minOf { it.second }
                val maxY = scaledPoints.takeLast(scaledPoints.size - 1).maxOf { it.second }
                val startY = minY
                val endY = minY + (maxY - minY)

                drawLine(
                    color = EnergyPrimaryBlue,
                    start = Offset(scaledPoints[i].first, startY),
                    end = Offset(scaledPoints[i].first, endY)
                )
                drawContext.canvas.nativeCanvas.drawTextLine(
                    TextLine.Companion.make(
                        texts[i].toString().plus("\u00B0"),
                        Font(typeface = Typeface.makeDefault(), size = textSize)
                    ),
                    x = scaledPoints[i].first - (textSize),
                    y = endY + marginBottom - textMarginTop,
                    Paint().apply {
                        isAntiAlias = true
                        color = 0xFF454444.toInt()
                    }
                )
            }
        }
        for (i in scaledPoints.indices) {
            if (i != scaledPoints.size - 1) {
                val startX = scaledPoints[i].first
                val startY = scaledPoints[i].second
                val endX = scaledPoints[i + 1].first
                val endY = scaledPoints[i + 1].second

                val midX = (startX + endX) / 2

                val firstControlPointX = midX
                val firstControlPointY = startY
                val secondControlPointX = midX
                val secondControlPointY = endY

                if (i == 0) {
                    val path = Path()
                    path.moveTo(startX, startY)
                    path.quadraticBezierTo(startX, startY, endX, endY)
                    drawPath(
                        path = path,
                        color = EnergyPrimaryBlue,
                        style = Stroke(width = 2f)
                    )
                } else {
                    drawBezierCurve(
                        startX = startX,
                        startY = startY,
                        firstControlPointX = firstControlPointX,
                        firstControlPointY = firstControlPointY,
                        secondControlPointX = secondControlPointX,
                        secondControlPointY = secondControlPointY,
                        endX = endX,
                        endY = endY
                    )
                }
            }
        }

        for (i in scaledPoints.indices) {
            if (i > 0) {
                drawCircle(
                    color = EnergyPrimaryBlue,
                    radius = 8f,
                    center = Offset(scaledPoints[i].first, scaledPoints[i].second)
                )
                if (i < scaledPoints.size - 1) {
                    drawCircle(
                        color = Color.White,
                        radius = 6f,
                        center = Offset(scaledPoints[i].first, scaledPoints[i].second)
                    )
                }
            }
        }
    }
}

fun DrawScope.drawBezierCurve(
    startX: Float,
    startY: Float,
    firstControlPointX: Float,
    firstControlPointY: Float,
    secondControlPointX: Float,
    secondControlPointY: Float,
    endX: Float,
    endY: Float
) {
    val path = Path()
    path.moveTo(startX, startY)
    path.cubicTo(
        firstControlPointX,
        firstControlPointY,
        secondControlPointX,
        secondControlPointY,
        endX,
        endY
    )
    drawPath(
        path = path,
        color = EnergyPrimaryBlue,
        style = Stroke(width = 2f)
    )
}

fun scalePoints(
    points: List<Pair<Float, Float>>,
    canvasWidth: Float,
    canvasHeight: Float
): List<Pair<Float, Float>> {
    val minX = points.minOf { it.first }
    val maxX = points.maxOf { it.first }
    val minY = points.minOf { it.second }
    val maxY = points.maxOf { it.second }

    val scaleX = canvasWidth / (maxX - minX)
    val scaleY = canvasHeight / (maxY - minY)

    return points.map { point ->
        Pair((point.first - minX) * scaleX, (point.second - minY) * scaleY)
    }
}
