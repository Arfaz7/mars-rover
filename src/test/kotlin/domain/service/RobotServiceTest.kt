package domain.service

import org.example.domain.model.Orientation
import org.example.domain.model.Robot
import org.example.domain.service.RobotService
import org.example.domain.service.RobotServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RobotServiceTest {
    lateinit var robotService: RobotService

    @BeforeEach
    fun setUp() {
        robotService = RobotServiceImpl()
    }

    @Nested
    inner class RobotInitialisation {
        @Test
        fun `creates a robot with valid initial position`() {
            val robot = robotService.initialiseRobot(x = 0, y = 1, orientation = "E")
            val expectedRobot = Robot(x = 0, y = 1, orientation = Orientation.E)
            assertEquals(expectedRobot, robot)
        }

        @Test
        fun `returns robot's position and orientation formatted`() {
            val robot = Robot(x = 0, y = 1, orientation = Orientation.E)
            val expectedDisplay = "(0, 1, E)"
            assertEquals(expectedDisplay, robot.toString())
        }

        @Test
        fun `returns robot's position, orientation and lost status formatted`() {
            val robot = Robot(x = 0, y = 1, orientation = Orientation.E, isLost = true)
            val expectedDisplay = "(0, 1, E) LOST"
            assertEquals(expectedDisplay, robot.toString())
        }

        @Test
        fun `throws an exception when a position is negative`() {
            assertThrows<IllegalArgumentException>("Position must be >= 0") {
                robotService.initialiseRobot(
                    x = -1,
                    y = 1,
                    orientation = "E"
                )

            }
        }

        @Test
        fun `throws an exception when orientation is not valid`() {
            assertThrows<IllegalArgumentException>("Orientation must be one of : 'N', 'E', 'W', 'S'") {
                robotService.initialiseRobot(
                    x = -1,
                    y = 1,
                    orientation = "L"
                )

            }
        }
    }

    @Nested
    inner class CommandComputation {
        @Test
        fun `executes F command with orientation N`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand('F')
            assertEquals(2, robot.y)
        }

        @Test
        fun `executes F command with orientation E`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.E)
            robotService.computeCommand('F')
            assertEquals(2, robot.x)
        }

        @Test
        fun `executes F command with orientation W`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.W)
            robotService.computeCommand('F')
            assertEquals(0, robot.x)
        }

        @Test
        fun `executes F command with orientation S`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.S)
            robotService.computeCommand('F')
            assertEquals(0, robot.y)
        }

        @Test
        fun `executes an L command`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand('L')
            assertEquals(Orientation.W, robot.orientation)
        }

        @Test
        fun `executes a R command`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand('R')
            assertEquals(Orientation.E, robot.orientation)
        }

        @Test
        fun `sets Robot as lost when out of bound and keep the last valid position`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand('F')
            assertTrue(robot.isLost)
        }

        @Test
        fun `throws an exception when command is unknown`() {
            assertThrows<IllegalArgumentException>("Command Z is unknown. Command must be F, L or R") {
                robotService.computeCommand(
                    'Z'
                )
            }
        }
    }
}
