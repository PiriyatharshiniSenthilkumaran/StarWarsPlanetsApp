package com.example.starwarsplanetsapp.data.repository

import androidx.paging.PagingData
import com.example.starwarsplanetsapp.data.remote.Planet
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {
    fun getPlanets(): Flow<PagingData<Planet>>
    suspend fun getPlanetDetails(url: String): Planet
}
