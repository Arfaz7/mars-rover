package org.example.application.commands

import org.example.domain.model.Mars

class ProcessMapInput {
    operator fun invoke(dimensions: IntArray): Mars {
        require(dimensions.size == 2) { "Map input should be of size 2" }
        return Mars(x = dimensions[0], y = dimensions[1])
    }
}
