package org.example.domain.service

import org.example.domain.model.Robot

interface RobotService {
    fun initialiseRobot(x: Int, y: Int, orientation: String): Robot
    fun computeCommand(robot: Robot, command: Char)
}
