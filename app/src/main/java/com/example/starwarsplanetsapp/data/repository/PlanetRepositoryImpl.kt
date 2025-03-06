package com.example.starwarsplanetsapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.starwarsplanetsapp.data.local.AppDatabase
import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.remote.PlanetApiService
import com.example.starwarsplanetsapp.data.remote.PlanetRemoteMediator
import com.example.starwarsplanetsapp.data.toPlanet
import com.example.starwarsplanetsapp.data.toPlanetEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: PlanetApiService,
    private val db: AppDatabase
) : PlanetRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPlanets(): Flow<PagingData<Planet>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PlanetRemoteMediator(api, db)
        ) {
            db.planetDao().pagingSource()
        }.flow.map { pagingData ->
            pagingData.map { it.toPlanet() }
        }
    }

    override suspend fun getPlanetDetails(url: String): Planet {
        // Check if the planet details are available in the local database
        val cachedPlanet =  db.planetDao().getPlanetByUrl(url)
        return if (cachedPlanet != null) {
            // Return the cached planet data
            cachedPlanet.toPlanet()
        } else {
            // Fetch from the network if not found in the local database
            try {
                val planetResponse = api.getPlanetDetails(url)
                val planetEntity = planetResponse.toPlanetEntity()
                // Insert the fetched data into the local database
                db.planetDao().insertPlanet(planetEntity)
                // Return the fetched planet data
                planetResponse
            } catch (e: Exception) {
                // Handle exceptions as needed
                throw e
            }
        }
    }

}