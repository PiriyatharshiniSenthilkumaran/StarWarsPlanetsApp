package com.example.starwarsplanetsapp

import androidx.paging.PagingData
import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.data.repository.PlanetRepository
import com.example.starwarsplanetsapp.ui.viewmodel.PlanetListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PlanetListViewModelTest {

    private lateinit var viewModel: PlanetListViewModel
    private val repository: PlanetRepository = mock()

    @Before
    fun setUp() {
        viewModel = PlanetListViewModel(repository)
    }

    @Test
    fun `planets should return Flow of PagingData`() = runTest {
        // Arrange
        val pagingData: Flow<PagingData<Planet>> = flowOf(PagingData.empty())
        `when`(repository.getPlanets()).thenReturn(pagingData)

        // Act
        val result = viewModel.planets

        // Assert
        assertEquals(pagingData, result)
    }
}