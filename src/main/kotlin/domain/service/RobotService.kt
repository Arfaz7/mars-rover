package org.example.domain.service

interface RobotService {
    fun initialiseRobot(x: Int, y: Int, orientation: String)
    fun computeCommand(command: Char)
}
