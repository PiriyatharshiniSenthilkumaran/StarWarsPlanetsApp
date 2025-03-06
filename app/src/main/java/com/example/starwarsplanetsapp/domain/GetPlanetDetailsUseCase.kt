package com.example.starwarsplanetsapp.domain

import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetDetailsUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(url: String): Planet {
        return repository.getPlanetDetails(url)
    }
}