package org.example.domain.service

import org.example.domain.model.Orientation
import org.example.domain.model.Robot

class RobotServiceImpl : RobotService {
    override fun initialiseRobot(x: Int, y: Int, orientation: Char): Robot {
        require(x >= 0 && y >= 0) { "Position must be >= 0" }
        require(orientation.uppercase() in "NEWS") { "Orientation must be one of : 'N', 'E', 'W', 'S'" }

        return Robot(x = x, y = y, orientation = Orientation.valueOf(orientation.uppercase()))
    }

    override fun computeCommand(robot: Robot, command: Char) {
        require(command.uppercaseChar() in "FLR") { "Command ${command.uppercaseChar()} is unknown. Command must be F, L or R" }

        when (command) {
            'F' -> moveRobot(robot)
            'L' -> rotateRobot(robot, -1)
            'R' -> rotateRobot(robot, 1)
        }
    }

    private fun moveRobot(robot: Robot) =
        when (robot.orientation) {
            Orientation.N -> robot.y++
            Orientation.E -> robot.x++
            Orientation.S -> robot.y--
            Orientation.W -> robot.x--
        }

    private fun rotateRobot(robot: Robot, direction: Byte) {
        val orientationSize = Orientation.values().size
        robot.orientation =
            Orientation.values()[(orientationSize + direction + robot.orientation.value) % orientationSize]
    }
}
