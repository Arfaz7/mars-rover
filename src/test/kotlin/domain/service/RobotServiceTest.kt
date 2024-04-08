package domain.service

import org.assertj.core.api.Assertions.assertThat
import org.example.domain.model.Orientation
import org.example.domain.model.Robot
import org.example.domain.service.RobotService
import org.example.domain.service.RobotServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
            val robot = robotService.initialiseRobot(x = 0, y = 1, orientation = 'E')
            val expectedRobot = Robot(x = 0, y = 1, orientation = Orientation.E)
            assertThat(robot).isEqualTo(expectedRobot)
        }

        @Test
        fun `returns robot's position and orientation formatted`() {
            val robot = Robot(x = 0, y = 1, orientation = Orientation.E)
            val expectedDisplay = "(0, 1, E)"
            assertThat(robot.toString()).isEqualTo(expectedDisplay)
        }

        @Test
        fun `returns robot's position, orientation and lost status formatted`() {
            val robot = Robot(x = 0, y = 1, orientation = Orientation.E, isLost = true)
            val expectedDisplay = "(0, 1, E) LOST"
            assertThat(robot.toString()).isEqualTo(expectedDisplay)
        }

        @Test
        fun `throws an exception when a position is negative`() {
            val exception = assertThrows<IllegalArgumentException> {
                robotService.initialiseRobot(
                    x = -1,
                    y = 1,
                    orientation = 'E'
                )

            }

            assertThat(exception.message).isEqualTo("Position must be >= 0")
        }

        @Test
        fun `throws an exception when orientation is not valid`() {
            val exception = assertThrows<IllegalArgumentException> {
                robotService.initialiseRobot(
                    x = 1,
                    y = 1,
                    orientation = 'L'
                )

            }
            assertThat(exception.message).isEqualTo("Orientation must be one of : 'N', 'E', 'W', 'S'")
        }
    }

    @Nested
    inner class CommandComputation {
        @Test
        fun `executes F command with orientation N`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand(robot, 'F')
            assertThat(robot.y).isEqualTo(2)
        }

        @Test
        fun `executes F command with orientation E`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.E)
            robotService.computeCommand(robot, 'F')
            assertThat(robot.x).isEqualTo(2)
        }

        @Test
        fun `executes F command with orientation W`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.W)
            robotService.computeCommand(robot, 'F')
            assertThat(robot.x).isEqualTo(0)
        }

        @Test
        fun `executes F command with orientation S`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.S)
            robotService.computeCommand(robot, 'F')
            assertThat(robot.y).isEqualTo(0)
        }

        @Test
        fun `executes an L command`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand(robot, 'L')
            assertThat(robot.orientation).isEqualTo(Orientation.W)
        }

        @Test
        fun `executes a R command`() {
            val robot = Robot(x = 1, y = 1, orientation = Orientation.N)
            robotService.computeCommand(robot, 'R')
            assertThat(robot.orientation).isEqualTo(Orientation.E)
        }

        @Test
        fun `throws an exception when command is unknown`() {
            val exception = assertThrows<Exception> {
                robotService.computeCommand(
                    Robot(x = 1, y = 1, orientation = Orientation.W),
                    'Z'
                )
            }

            assertThat(exception.message).isEqualTo("Command Z is unknown. Command must be F, L or R")
        }
    }
}
