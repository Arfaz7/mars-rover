package org.example.domain.service

import org.example.domain.model.Mars

class PlanetServiceImpl : PlanetService {
    override fun initialiseDimension(x: Int, y: Int): Mars {
        require(x > 0 && y > 0)
        return Mars(x, y)
    }
}
