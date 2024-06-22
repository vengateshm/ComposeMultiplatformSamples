package samples.viewmodel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import composemultiplatformsamples.composeapp.generated.resources.Res
import composemultiplatformsamples.composeapp.generated.resources.counter_name
import org.jetbrains.compose.resources.stringResource

@Composable
fun ViewModelSample(modifier: Modifier = Modifier, viewModel: CounterViewModel = viewModel()) {
    val state = viewModel.state
    CounterApp(
        count = state.intValue,
        onIncrement = viewModel::increment,
        onDecrement = viewModel::decrement
    )
}

@Composable
fun CounterApp(
    modifier: Modifier = Modifier,
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = stringResource(Res.string.counter_name))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = onIncrement) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = count.toString())
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedButton(onClick = onDecrement) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                }
            }
        }
    }
}

class CounterViewModel : ViewModel() {

    private val _state = mutableIntStateOf(0)
    val state = _state.asIntState()

    fun increment() {
        _state.intValue = state.intValue + 1
    }

    fun decrement() {
        if (state.value <= 0) return
        _state.intValue = state.intValue - 1
    }

    override fun onCleared() {
        super.onCleared()
        println("CounterViewModel onCleared() called")
    }
}