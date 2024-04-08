package application.commands

import org.assertj.core.api.Assertions.assertThat
import org.example.application.commands.ProcessRobotInput
import org.example.domain.model.Mars
import org.example.domain.model.Orientation
import org.example.domain.model.Robot
import org.junit.jupiter.api.Test

class ProcessRobotInputTest {
    val processRobotInput = ProcessRobotInput(Mars(7, 8))

    @Test
    fun `creates robot without moving it`() {
        val robot = processRobotInput(input = "(0, 0, N)")
        val expectedRobot = Robot(x = 0, y = 0, orientation = Orientation.N)

        assertThat(robot).isEqualTo(expectedRobot)
    }

    @Test
    fun `creates robot and moves it`() {
        val robot = processRobotInput(input = "(0, 0, N) FFRF")
        val expectedRobot = Robot(x = 1, y = 2, orientation = Orientation.E)

        assertThat(robot).isEqualTo(expectedRobot)
    }

    @Test
    fun `moves a robot out of the map and returns its last valid position`() {
        val robot = processRobotInput(input = "(0, 0, N) LFRF")
        val expectedRobot = Robot(x = 0, y = 0, orientation = Orientation.W, isLost = true)

        assertThat(robot).isEqualTo(expectedRobot)
    }
}
