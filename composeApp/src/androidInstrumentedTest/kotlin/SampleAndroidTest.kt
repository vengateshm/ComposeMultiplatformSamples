import android.content.Context
import androidx.test.core.app.ApplicationProvider
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class SampleAndroidTest {
    @Test
    fun testPackageName() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertThat(context.packageName).isEqualTo("dev.vengateshm.compose.multiplatform.samples")
    }
}