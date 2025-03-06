package com.example.starwarsplanetsapp.domain

import androidx.paging.PagingData
import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(): Flow<PagingData<Planet>> {
        return repository.getPlanets()
    }
}