package org.example

import org.example.application.commands.ProcessMapInput
import org.example.application.commands.ProcessRobotInput
import org.example.domain.model.Mars
import org.example.domain.model.Robot

class Main

fun main(args: Array<String>) {
    var mars: Mars
    val robots = mutableListOf<Robot>()
    val processMapInput = ProcessMapInput()

    if (args.isEmpty()) {
        println("No input passed to the program")
        return
    }

    val mapDimensions: IntArray = args[0].split(" ").mapNotNull { it.toIntOrNull() }.toIntArray()
    mars = processMapInput(mapDimensions)

    val processRobotInput = ProcessRobotInput(mars)
    args.drop(1).forEach { robots.add(processRobotInput(it)) }

    for (robot in robots) {
        println(robot)
    }
}

