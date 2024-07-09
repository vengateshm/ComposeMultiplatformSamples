package samples.ktor_http_client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import utils.onError
import utils.onSuccess

@Composable
fun CensorTextSample(
    censorService: CensorService
) {
    var isLoading by remember { mutableStateOf(false) }
    var unCensoredText by remember { mutableStateOf("") }
    var censoredText by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = unCensoredText,
            onValueChange = {
                unCensoredText = it
            }
        )
        Button(
            enabled = isLoading.not(),
            onClick = {
                isLoading = true
                coroutineScope.launch {
                    censorService.censorWords(unCensoredText)
                        .onSuccess {
                            isLoading = false
                            errorText = ""
                            censoredText = it
                        }
                        .onError {
                            isLoading = false
                            censoredText = ""
                            errorText = it.name
                        }
                }
            },
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "Censor text")
            Spacer(modifier = Modifier.width(8.dp))
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }
        Text(text = censoredText)
    }
}