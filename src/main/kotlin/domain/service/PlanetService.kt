package org.example.domain.service

import org.example.domain.model.Mars

interface PlanetService {
    fun initialiseDimension(x: Int, y: Int): Mars
}
