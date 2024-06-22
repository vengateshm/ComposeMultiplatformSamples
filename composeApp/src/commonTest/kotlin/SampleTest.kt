import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import samples.ui_testing.UiTestingSample
import kotlin.test.Test

class SampleTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun sampleTest() = runComposeUiTest {
        setContent {
            UiTestingSample()
        }

        onNodeWithTag("text").assertTextEquals("0", includeEditableText = false)
        onNodeWithTag("button").assertTextEquals("Click me!")
        onNodeWithTag("button").performClick()
        onNodeWithTag("text").assertTextEquals("1", includeEditableText = false)
        repeat(4) {
            onNodeWithTag("button").performClick()
        }
        onNodeWithTag("text").assertTextEquals("Premium", includeEditableText = false)
    }
}