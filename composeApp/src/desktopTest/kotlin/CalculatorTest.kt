import assertk.assertThat
import assertk.assertions.isEqualTo
import test.Calculator
import kotlin.test.Test

class CalculatorTest {
    @Test
    fun testSum() {
        val result = Calculator().sum(2, 3)
        assertThat(result).isEqualTo(5)
    }
}