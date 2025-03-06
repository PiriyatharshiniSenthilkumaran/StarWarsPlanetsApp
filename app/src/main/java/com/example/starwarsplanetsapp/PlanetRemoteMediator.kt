package com.example.starwarsplanetsapp

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction

@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val api: PlanetApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, Planet>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Planet>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id?.substringAfterLast("/")?.toInt()?.div(10)?.plus(1) ?: 1
                }
            }

            val response = api.getPlanets(page)
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.planetDao().clearAll()
                }
                db.planetDao().insertAll(response.results)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.next == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}