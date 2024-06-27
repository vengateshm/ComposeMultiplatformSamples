import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import org.jetbrains.skia.Font
import org.jetbrains.skia.Paint
import org.jetbrains.skia.TextLine

@Composable
fun BezierCurveCanvas() {
    val points = remember {
        listOf(
            Pair(19f, 109f),
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

    Canvas(modifier = Modifier.size(300.dp, 200.dp)) {
        for (i in points.indices) {
            if (i > 0) {
                val minY = points.takeLast(points.size - 1).minOf { it.second }
                val maxY = points.takeLast(points.size - 1).maxOf { it.second }
                val startY = minY - 20f
                val endY = minY + (maxY - minY) + 20f

                drawLine(
                    color = Color.Blue,
                    start = Offset(points[i].first, startY),
                    end = Offset(points[i].first, endY)
                )
                drawContext.canvas.nativeCanvas.drawTextLine(
                    TextLine.Companion.make(texts[i].toString().plus("\u00B0"), Font()),
                    points[i].first - 10f,
                    105f + (139f - 105f) + 40f,
                    Paint().apply {
                        isAntiAlias = true
                    }
                )
            }
        }
        for (i in points.indices) {
            if (i != points.size - 1) {
                val startX = points[i].first
                val startY = points[i].second
                val endX = points[i + 1].first
                val endY = points[i + 1].second

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
                        color = Color.Blue,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)
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

        for (i in points.indices) {
            if (i > 0) {
                drawCircle(
                    color = Color.Blue,
                    radius = 8f,
                    center = Offset(points[i].first, points[i].second)
                )
                if (i < points.size - 1) {
                    drawCircle(
                        color = Color.White,
                        radius = 6f,
                        center = Offset(points[i].first, points[i].second)
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
        color = Color.Blue,
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)
    )
}