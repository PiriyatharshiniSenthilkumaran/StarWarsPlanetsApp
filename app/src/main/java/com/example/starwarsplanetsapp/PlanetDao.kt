package com.example.starwarsplanetsapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.paging.PagingSource

@Dao
interface PlanetDao {
    @Query("SELECT * FROM planets ORDER BY name")
    fun pagingSource(): PagingSource<Int, Planet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(planets: List<Planet>)

    @Query("DELETE FROM planets")
    suspend fun clearAll()

    @Query("SELECT * FROM planets WHERE id = :url")
    suspend fun getPlanetById(url: String): Planet?
}