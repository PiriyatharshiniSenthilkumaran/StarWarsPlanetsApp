package com.example.starwarsplanetsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planets")
data class PlanetEntity(
    @PrimaryKey val id: String,  // Use URL as ID
    val name: String,
    val climate: String,
    val orbitalPeriod: String,
    val gravity: String
)


