import assertk.assertThat
import assertk.assertions.isEqualTo
import testing.getInitials
import kotlin.test.Test

class GetInitialsTest {

    @Test
    fun testGetInitials() {
        val actual = getInitials("Aaron Finch")
        assertThat(actual).isEqualTo("AF")
    }
}