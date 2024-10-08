package samples.animation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.toOffset
import kotlin.math.abs
import kotlin.random.Random

data class Ball(val position: Offset, val speed: Offset, val radius: Float, val color: Color)
data class BallModel(val balls: List<Ball>)

const val oneSecondNs = (1 * 1000 * 1000 * 1000).toFloat()
val gravity = Offset(0f, 200f)

@Composable
fun ContinuousAnimationSample(modifier: Modifier = Modifier) {
    var size = IntSize(0, 0)
    var ball by remember {
        mutableStateOf(
            Ball(
                position = Offset(100f, 100f),
                speed = Offset(200f, 0f),
                radius = 15f,
                color = Color.Red,
            ),
        )
    }
    var ballModel by remember { mutableStateOf(BallModel(listOf(ball))) }
    LaunchedEffect(true) {
        var last = withFrameNanos { it }
        while (true) {
            val current = withFrameNanos { it }
            val elapsed = current - last
            last = current
            ballModel =
                BallModel(
                    ballModel.balls.map { updateBall(elapsed, it, size) }.toList() + newBall(
                        size
                    )
                )
            updateBall(elapsed, ball, size)
        }
    }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned {
            size = it.size
        }) {
        ballModel.balls.forEach {
            drawBall(it)
        }
    }
}

val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan)

private fun newBall(size: IntSize): Ball {
    val dx = (Random.nextDouble() - 0.5) * 2 * 400
    val dy = (Random.nextDouble() - 0.5) * 2 * 400
    return Ball(size.center.toOffset(), Offset(dx.toFloat(), dy.toFloat()), 15f, colors.random())
}

private fun updateBall(
    elapsed: Long,
    ball: Ball,
    size: IntSize
): Ball {
    val partOfSecond = elapsed / oneSecondNs
    var newSpeed = ball.speed + (gravity * partOfSecond)
    val newPosition = ball.position + (ball.speed * partOfSecond)

    if (size.height < (newPosition.y + ball.radius)) {
        newSpeed = Offset(newSpeed.x, -abs(newSpeed.y))
    }
    if (size.width < (newPosition.x + ball.radius)) {
        newSpeed = Offset(-abs(newSpeed.x), newSpeed.y)
    }
    if (0 > (newPosition.x - ball.radius)) {
        newSpeed = Offset(abs(newSpeed.x), newSpeed.y)
    }
    return ball.copy(
        position = newPosition,
        speed = newSpeed,
    )
}

private fun DrawScope.drawBall(ball: Ball) {
    drawCircle(color = ball.color, center = ball.position, radius = ball.radius)
    drawCircle(
        color = Color.Black,
        center = ball.position,
        radius = ball.radius,
        style = Stroke(1f),
    )
}