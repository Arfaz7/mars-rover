package org.example.application.commands

import org.example.domain.model.Mars
import org.example.domain.model.Robot
import org.example.domain.service.RobotServiceImpl

class ProcessRobotInput(private val mars: Mars) {
    private val robotService = RobotServiceImpl()

    operator fun invoke(input: String): Robot {
        val positions = extractRobotPosition(input)
        var robot = robotService.initialiseRobot(
            x = positions[0].digitToInt(),
            y = positions[1].digitToInt(),
            orientation = positions[2]
        )

        val commands = extractRobotCommands(input)
        for (command in commands) {
            val previousStateRobot = robot.copy()
            robotService.computeCommand(robot, command)

            if (isRobotLost(robot)) {
                robot = previousStateRobot.copy(isLost = true)
                break
            }
        }

        return robot
    }

    private fun extractRobotCommands(input: String): Array<Char> =
        input.split(")")[1].trim().toCharArray().toTypedArray()

    private fun extractRobotPosition(input: String): Array<Char> =
        input.split(")")[0].drop(1).split(",").map { it.trim().single() }.toTypedArray()

    private fun isRobotLost(robot: Robot): Boolean =
        robot.x < 0 || robot.y < 0 || robot.x > mars.x || robot.y > mars.y

}
