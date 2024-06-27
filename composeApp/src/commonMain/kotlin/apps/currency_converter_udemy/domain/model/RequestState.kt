package apps.currency_converter_udemy.domain.model

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()

    fun isLoading() = this is Loading
    fun isError() = this is Error
    fun isSuccess() = this is Success

    fun getSuccessData() = (this as Success).data
    fun getErrorMessage() = (this as Error).message
}

@Composable
fun <T> RequestState<T>.DisplayResult(
    onIdle: (@Composable () -> Unit)? = null,
    onLoading: (@Composable () -> Unit)? = null,
    onSuccess: (@Composable (T) -> Unit)? = null,
    onError: (@Composable (String) -> Unit)? = null,
    transitionSpec: ContentTransform =
        scaleIn(tween(durationMillis = 400)) +
                fadeIn(tween(durationMillis = 800))
                togetherWith
                scaleOut(tween(durationMillis = 400)) +
                fadeOut(tween(durationMillis = 800))
) {
    AnimatedContent(
        targetState = this,
        transitionSpec = { transitionSpec },
        label = "Content Animation"
    ) { state ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (state) {
                is RequestState.Error -> {
                    onError?.invoke(state.getErrorMessage())
                }

                RequestState.Idle -> {
                    onIdle?.invoke()
                }

                RequestState.Loading -> {
                    onLoading?.invoke()
                }

                is RequestState.Success -> {
                    onSuccess?.invoke(state.getSuccessData())
                }
            }
        }
    }
}