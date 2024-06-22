package samples.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.eventFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LifecycleLogger() {
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(lifecycle) {
        launch {
            lifecycle.lifecycle.currentStateFlow.collectLatest {
                println("State : $it")
            }
        }
        launch {
            lifecycle.lifecycle.eventFlow.collectLatest {
                //println("Event : $it")
            }
        }
    }
}