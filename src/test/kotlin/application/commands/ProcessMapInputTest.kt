package application.commands

import org.assertj.core.api.Assertions.assertThat
import org.example.application.commands.ProcessMapInput
import org.example.domain.model.Mars
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProcessMapInputTest {
    val processMapInput = ProcessMapInput()

    @Test
    fun `creates a map`() {
        val mars = processMapInput(intArrayOf(7, 10))
        val expectedMars = Mars(x = 7, y = 10)
        assertThat(mars).isEqualTo(expectedMars)
    }

    @Test
    fun `throws an exception when map dimension input is invalid`() {
        val exception = assertThrows<IllegalArgumentException> { processMapInput(intArrayOf(1)) }
        assertThat(exception.message).isEqualTo("Map input should be of size 2")
    }
}
