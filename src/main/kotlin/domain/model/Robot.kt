package org.example.domain.model

data class Robot(val x: Int, val y: Int, val orientation: Orientation, val isLost: Boolean = false)
