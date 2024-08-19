package samples.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data object Scr1

@Serializable
data class Scr2(val msg: String)

@Composable
fun NavigationTypeSafeSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Scr1
    ) {
        composable<Scr1> {
            S1 {
                navController.navigate(Scr2(msg = "Secret"))
            }
        }
        composable<Scr2> {
            val data = it.toRoute<Scr2>()
            S2(message = data.msg) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun S1(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 1",
            modifier = Modifier.clickable { onClick() })
    }
}

@Composable
fun S2(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 2 :: $message",
            modifier = Modifier.clickable { onClick() })
    }
}