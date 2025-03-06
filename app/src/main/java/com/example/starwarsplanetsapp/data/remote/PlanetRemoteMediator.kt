package com.example.starwarsplanetsapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.starwarsplanetsapp.data.local.AppDatabase
import com.example.starwarsplanetsapp.data.local.PlanetEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val api: PlanetApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, PlanetEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlanetEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    // Safely extract the page number from the URL
                    val lastItemId = lastItem?.id?.substringAfterLast("/")?.trim()
                    if (!lastItemId.isNullOrEmpty()) {
                        try {
                            lastItemId.toInt().div(10) + 1  // Calculate the next page
                        } catch (e: NumberFormatException) {
                            1  // Fallback to page 1 if parsing fails
                        }
                    } else {
                        1  // Fallback to page 1 if the ID is invalid or empty
                    }
                    
                }
            }

            // Fetch data from the API
            val response = api.getPlanets(page)

            // Convert API response (Planet) to Room entity (PlanetEntity)
            val planetEntities = response.results.map { planet ->
                PlanetEntity(
                    id = planet.id,  // Use the URL as the ID
                    name = planet.name,
                    climate = planet.climate,
                    orbitalPeriod = planet.orbitalPeriod,
                    gravity = planet.gravity
                )
            }

            // Save data to the database
            withContext(Dispatchers.IO) {
                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        db.planetDao().clearAll()
                    }
                    db.planetDao().insertAll(planetEntities)
                }
            }

            // Check if we've reached the end of pagination
            MediatorResult.Success(
                endOfPaginationReached = response.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}