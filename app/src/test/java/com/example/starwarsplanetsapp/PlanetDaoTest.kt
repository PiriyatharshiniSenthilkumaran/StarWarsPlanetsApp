package com.example.starwarsplanetsapp

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarsplanetsapp.data.local.AppDatabase
import com.example.starwarsplanetsapp.data.local.PlanetDao
import com.example.starwarsplanetsapp.data.local.PlanetEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlanetDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var planetDao: PlanetDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        planetDao = db.planetDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insertAll and pagingSource should work`() = runTest {
        // Arrange
        val planetEntity = PlanetEntity(
            id = "https://swapi.dev/api/planets/1/",
            name = "Tatooine",
            climate = "arid",
            orbitalPeriod = "304",
            gravity = "1 standard"
        )

        // Act
        planetDao.insertAll(listOf(planetEntity))
        val pagingSource = planetDao.pagingSource()

        // Load data from the PagingSource
        val loadParams = PagingSource.LoadParams.Refresh(
            key = 0, // Use an Int key here
            loadSize = 10,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(loadParams)

        // Assert
        when (result) {
            is PagingSource.LoadResult.Page -> {
                assertEquals(1, result.data.size)  // Verify the number of items
                assertEquals(planetEntity, result.data[0])  // Verify the item
            }

            else -> throw AssertionError("Expected LoadResult.Page")
        }
    }
}