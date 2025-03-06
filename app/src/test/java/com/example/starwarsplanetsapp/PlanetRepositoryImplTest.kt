package com.example.starwarsplanetsapp


import androidx.paging.PagingData
import com.example.starwarsplanetsapp.data.local.AppDatabase
import com.example.starwarsplanetsapp.data.local.PlanetDao
import com.example.starwarsplanetsapp.data.local.PlanetEntity
import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.remote.PlanetApiService
import com.example.starwarsplanetsapp.data.repository.PlanetRepositoryImpl
import com.example.starwarsplanetsapp.data.toPlanet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class PlanetRepositoryImplTest {

    private lateinit var repository: PlanetRepositoryImpl
    private val api: PlanetApiService = mock()
    private val db: AppDatabase = mock()
    private val planetDao: PlanetDao = mock()

    @Before
    fun setUp() {
        `when`(db.planetDao()).thenReturn(planetDao)
        repository = PlanetRepositoryImpl(api, db)
    }

    @Test
    fun `getPlanets should return Flow of PagingData`() = runBlocking {
        // Arrange
        val planetEntity = PlanetEntity(
            id = "https://swapi.dev/api/planets/1/",
            name = "Tatooine",
            climate = "arid",
            orbitalPeriod = "304",
            gravity = "1 standard"
        )
        val pagingData: Flow<PagingData<Planet>> = flowOf(PagingData.from(listOf(planetEntity.toPlanet())))
        `when`(planetDao.pagingSource()).thenReturn(mock())

        // Act
        val result = repository.getPlanets()

        // Assert
        assertEquals(pagingData, result)
    }

    @Test
    fun `getPlanetDetails should return Planet from API`(): Unit = runBlocking {
        // Arrange
        val url = "https://swapi.dev/api/planets/1/"
        val planet = Planet(
            id = url,
            name = "Tatooine",
            climate = "arid",
            orbitalPeriod = "304",
            gravity = "1 standard"
        )
        `when`(api.getPlanetDetails(url)).thenReturn(planet)

        // Act
        val result = repository.getPlanetDetails(url)

        // Assert
        assertEquals(planet, result)
        verify(api).getPlanetDetails(url)
    }
}