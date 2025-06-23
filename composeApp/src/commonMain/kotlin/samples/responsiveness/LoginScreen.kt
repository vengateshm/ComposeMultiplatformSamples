package samples.responsiveness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import samples.design_system.AppButton
import samples.design_system.AppLink
import samples.design_system.AppTextField

@Composable
fun LoginScreen() {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val rootModifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp
                )
            )
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            )
            .consumeWindowInsets(WindowInsets.navigationBars)
            .padding(top = 48.dp)
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                Column(
                    modifier = rootModifier,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    LoginHeaderSection()
                    LoginFormSection(
                        emailText = emailText,
                        onEmailTextChange = { emailText = it },
                        passwordText = passwordText,
                        onPasswordTextChange = { passwordText = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                Row(
                    modifier = rootModifier
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    LoginHeaderSection(modifier = Modifier.weight(1f))
                    LoginFormSection(
                        emailText = emailText,
                        onEmailTextChange = { emailText = it },
                        passwordText = passwordText,
                        onPasswordTextChange = { passwordText = it },
                        modifier = Modifier.weight(1f)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
            DeviceConfiguration.TABLET_PORTRAIT ,
            DeviceConfiguration.TABLET_LANDSCAPE ,
            DeviceConfiguration.DESKTOP -> {
                Column(
                    modifier = rootModifier
                        .verticalScroll(rememberScrollState())
                        .padding(top = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginHeaderSection(
                        modifier = Modifier.widthIn(540.dp),
                        alignment = Alignment.CenterHorizontally
                    )
                    LoginFormSection(
                        emailText = emailText,
                        onEmailTextChange = { emailText = it },
                        passwordText = passwordText,
                        onPasswordTextChange = { passwordText = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun LoginHeaderSection(
    alignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier,
        horizontalAlignment = alignment) {
        Text(
            text = "Log In",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Welcome to really cool app!",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun LoginFormSection(
    emailText: String,
    onEmailTextChange: (String) -> Unit,
    passwordText: String,
    onPasswordTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        AppTextField(
            text = emailText,
            onValueChange = onEmailTextChange,
            label = "Email",
            hint = "jon.doe@example.com",
            isInputSecret = false,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        AppTextField(
            text = passwordText,
            onValueChange = onPasswordTextChange,
            label = "Password",
            hint = "Password",
            isInputSecret = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
        AppButton(
            text = "Log In",
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        AppLink(
            text = "Don't have an account?",
            onClick = {},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}