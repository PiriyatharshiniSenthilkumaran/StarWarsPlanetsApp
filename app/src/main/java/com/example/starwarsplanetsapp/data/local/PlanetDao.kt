package com.example.starwarsplanetsapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlanetDao {
    @Query("SELECT * FROM planets ORDER BY name")
    fun pagingSource(): PagingSource<Int, PlanetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: List<PlanetEntity>)

    @Query("SELECT * FROM planets WHERE id = :url LIMIT 1")
    suspend fun getPlanetByUrl(url: String): PlanetEntity?

    @Query("DELETE FROM planets")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanet(planetEntity: PlanetEntity)
}