package org.example.domain.model

data class Robot(var x: Int, var y: Int, var orientation: Orientation, var isLost: Boolean = false) {
    override fun toString(): String {
        return "($x, $y, $orientation)" + if (isLost) " LOST" else ""
    }
}
