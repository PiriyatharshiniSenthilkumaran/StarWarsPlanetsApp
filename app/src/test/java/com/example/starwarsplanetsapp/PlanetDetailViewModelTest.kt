package com.example.starwarsplanetsapp

import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.repository.PlanetRepository
import com.example.starwarsplanetsapp.ui.viewmodel.PlanetDetailViewModel
import org.mockito.Mockito.verify


import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PlanetDetailViewModelTest {

    private lateinit var viewModel: PlanetDetailViewModel
    private val repository: PlanetRepository = mock()

    @Before
    fun setUp() {
        viewModel = PlanetDetailViewModel(repository)
    }

    @Test
    fun `loadPlanetDetails should update planet state`() = runTest {
        // Arrange
        val url = "https://swapi.dev/api/planets/1/"
        val planet = Planet(
            id = url,
            name = "Tatooine",
            climate = "arid",
            orbitalPeriod = "304",
            gravity = "1 standard"
        )
        `when`(repository.getPlanetDetails(url)).thenReturn(planet)

        // Act
        viewModel.loadPlanetDetails(url)
        val result = viewModel.planetState.first()

        // Assert
        assertEquals(planet, result)
        verify(repository).getPlanetDetails(url)
    }
}