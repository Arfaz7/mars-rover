package domain.service

import org.assertj.core.api.Assertions.assertThat
import org.example.domain.model.Mars
import org.example.domain.service.PlanetService
import org.example.domain.service.PlanetServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlanetServiceTest {
    lateinit var planetService: PlanetService

    @BeforeEach
    fun setUp() {
        planetService = PlanetServiceImpl()
    }

    @Test
    fun `creates a planet with valid dimensions`() {
        val mars = planetService.initialiseDimension(x = 10, y = 8)
        val expectedMars = Mars(x = 10, y = 8)
        assertThat(expectedMars).isEqualTo(mars)
    }

    @Test
    fun `throws an exception if x=0`() {
        val exception = assertThrows<IllegalArgumentException> { planetService.initialiseDimension(x = 0, y = 12) }
        assertThat(exception.message).isEqualTo("Dimensions must be > 0")
    }

    @Test
    fun `throws an exception if y=0`() {
        val exception = assertThrows<IllegalArgumentException> { planetService.initialiseDimension(x = 7, y = 0) }
        assertThat(exception.message).isEqualTo("Dimensions must be > 0")
    }

    @Test
    fun `throws an exception if a dimension is negative`() {
        val exception = assertThrows<IllegalArgumentException> { planetService.initialiseDimension(x = -2, y = 3) }
        assertThat(exception.message).isEqualTo("Dimensions must be > 0")
    }
}
