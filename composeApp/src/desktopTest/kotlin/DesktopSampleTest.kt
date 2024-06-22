import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import samples.ui_testing.UiTestingSample
import kotlin.test.Test

class DesktopSampleTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun myTest() {
        rule.setContent {
            UiTestingSample()
        }
        rule.onNodeWithTag("text").assertTextEquals("0", includeEditableText = false)
        rule.onNodeWithTag("button").assertTextEquals("Click me!")
        rule.onNodeWithTag("button").performClick()
        rule.onNodeWithTag("text").assertTextEquals("1", includeEditableText = false)
        repeat(4) {
            rule.onNodeWithTag("button").performClick()
        }
        rule.onNodeWithTag("text").assertTextEquals("Premium", includeEditableText = false)
    }
}