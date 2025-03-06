package com.example.starwarsplanetsapp.data

import com.example.starwarsplanetsapp.data.local.PlanetEntity
import com.example.starwarsplanetsapp.data.remote.Planet


/**
 * Extension function to convert PlanetEntity to Planet.
 */
fun PlanetEntity.toPlanet(): Planet {
    return Planet(
        id = id,
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity
    )
}

/**
 * Extension function to convert Planet to PlanetEntity.
 */
fun Planet.toPlanetEntity(): PlanetEntity {
    return PlanetEntity(
        id = id,
        name = name,
        climate = climate,
        orbitalPeriod = orbitalPeriod,
        gravity = gravity
    )
}