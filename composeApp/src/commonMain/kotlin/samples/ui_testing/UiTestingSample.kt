package samples.ui_testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UiTestingSample(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }

    val isPremium by remember {
        derivedStateOf {
            count != 0 && count % 5 == 0
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.testTag("text"),
            text = if (isPremium) "Premium" else count.toString(),
            color = if (isPremium) Color(0XFF00FF00) else Color(0XFF0000FF),
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        ElevatedButton(
            modifier = Modifier.testTag("button"),
            onClick = {
                count++
            }) {
            Text(
                text = "Click me!",
                fontSize = 32.sp
            )
        }
    }
}