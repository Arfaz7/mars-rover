package org.example.domain.service

import org.example.domain.model.Orientation
import org.example.domain.model.Robot

class RobotServiceImpl: RobotService {
    override fun initialiseRobot(x: Int, y: Int, orientation: String): Robot {
        require(x >= 0 && y >= 0) {"Position must be >= 0"}
        require(orientation.uppercase() in "NEWS") {"Orientation must be one of : 'N', 'E', 'W', 'S'"}

        return Robot(x = x, y = y, orientation = Orientation.valueOf(orientation))
    }

    override fun computeCommand(command: Char) {
        TODO("Not yet implemented")
    }
}
