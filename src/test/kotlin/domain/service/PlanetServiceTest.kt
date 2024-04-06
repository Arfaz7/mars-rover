package domain.service

import org.example.domain.model.Mars
import org.example.domain.service.PlanetService
import org.example.domain.service.PlanetServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

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
        assertEquals(expectedMars, mars)
    }

    @Test
    fun `throws an exception if x=0`() {
        assertThrows<IllegalArgumentException>("Dimensions must be > 0 ") { planetService.initialiseDimension(x = 0, y = 12) }
    }

    @Test
    fun `throws an exception if y=0`() {
        assertThrows<IllegalArgumentException>("Dimensions must be > 0 ") { planetService.initialiseDimension(x = 7, y = 0) }
    }

    @Test
    fun `throws an exception if a dimension is negative`() {
        assertThrows<IllegalArgumentException>("Dimensions must be > 0 ") { planetService.initialiseDimension(x = -2, y = 3) }
    }
}
