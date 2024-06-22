import androidx.compose.runtime.Composable
import apps.whatsapp.model.WindowSize
import samples.lifecycle.LifecycleSample
import samples.navigation.NavigationSample
import samples.ui_testing.UiTestingSample
import samples.window.WindowInsetsSample

@Composable
fun App(windowSize: WindowSize? = null) {
    WindowInsetsSample()
}