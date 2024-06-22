package samples.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationSample(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.background(color = Color(0XFFE4FBE7)),
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            HomeScreen(
                onGoToProfile = {
                    navController.navigate("Profile")
                },
                onOpenInfoDialog = {
                    navController.navigate("info/$it")
                })
        }
        composable(route = "Profile") {
            ProfileScreen(onGoBackClick = dropUnlessResumed {
                navController.popBackStack()
            })
        }

        dialog(
            route = "info/{message}"
        ) { backStackEntry ->
            val message = backStackEntry.arguments?.getString("message") ?: "No info at this time!"
            InfoDialog(
                message = message,
                onOkClick = dropUnlessResumed {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onGoToProfile: () -> Unit,
    onOpenInfoDialog: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home")
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onGoToProfile,
        ) {
            Text(text = "Go To Profile")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                onOpenInfoDialog("You've just opened a dialog!")
            },
        ) {
            Text(text = "Open Dialog")
        }
    }
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onGoBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile")
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onGoBackClick,
        ) {
            Text(text = "Go Back")
        }
    }
}

@Composable
fun InfoDialog(
    modifier: Modifier = Modifier, message: String,
    onOkClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color(0XFFE4FBE7))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Info",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = message,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onOkClick) {
                    Text(
                        text = "OK",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
