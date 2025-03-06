package com.example.starwarsplanetsapp

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.starwarsplanetsapp.data.local.AppDatabase
import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.remote.PlanetApiService
import com.example.starwarsplanetsapp.data.remote.PlanetRemoteMediator
import com.example.starwarsplanetsapp.data.remote.PlanetResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PlanetRemoteMediatorTest {

    private lateinit var mediator: PlanetRemoteMediator
    private val api: PlanetApiService = mock()
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        mediator = PlanetRemoteMediator(api, db)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `load should return MediatorResult Success`() = runTest {
        // Arrange
        val planetResponse = PlanetResponse(
            count = 1,
            next = null,
            results = listOf(
                Planet(
                    id = "https://swapi.dev/api/planets/1/",
                    name = "Tatooine",
                    climate = "arid",
                    orbitalPeriod = "304",
                    gravity = "1 standard"
                )
            )
        )
        `when`(api.getPlanets(1)).thenReturn(planetResponse)

        // Act
        val result = mediator.load(
            LoadType.REFRESH,
            PagingState(listOf(), null, PagingConfig(10), 0)
        )

        // Assert
        assertEquals(RemoteMediator.MediatorResult.Success(endOfPaginationReached = true), result)
    }
}