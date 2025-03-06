package com.example.starwarsplanetsapp

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class PlanetRepository(
    private val api: PlanetApiService,
    private val db: AppDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPlanetsPagingFlow(): Flow<PagingData<Planet>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PlanetRemoteMediator(api, db)
        ) {
            db.planetDao().pagingSource()
        }.flow
    }

    suspend fun getPlanetDetails(url: String): Planet {
        return try {
            api.getPlanetDetails(url).also {
                db.planetDao().insertAll(listOf(it))
            }
        } catch (e: Exception) {
            db.planetDao().getPlanetById(url) ?: throw e
        }
    }
}