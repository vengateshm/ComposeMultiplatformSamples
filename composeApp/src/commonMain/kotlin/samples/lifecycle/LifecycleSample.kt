package samples.lifecycle

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

val counterFlow = flow {
    var count = 0
    while (true) {
        emit(++count)
        delay(1000L)
    }
}

@Composable
fun LifecycleSample(
    modifier: Modifier = Modifier,
    viewModel: LifecycleSampleViewModel = viewModel()
) {
    LifecycleLogger()
    val count by counterFlow.collectAsStateWithLifecycle(
        initialValue = 0,
        minActiveState = Lifecycle.State.CREATED
    )
//    val count by viewModel.counterFlow.collectAsStateWithLifecycle(initialValue = 0)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(targetState = count) {
            Text(
                text = "Counting... $count",
                fontSize = 48.sp
            )
        }
    }
}

class LifecycleSampleViewModel : ViewModel() {
    private val _counterFlow = MutableStateFlow(0)
    val counterFlow: StateFlow<Int> = _counterFlow

    init {
        startCounting()
    }

    private fun startCounting() {
        viewModelScope.launch {
            while (true) {
                _counterFlow.value += 1
                delay(1000L)
            }
        }
    }
}