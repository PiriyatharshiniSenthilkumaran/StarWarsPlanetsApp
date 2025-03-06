package com.example.starwarsplanetsapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "planets")
@JsonClass(generateAdapter = true)
data class Planet(
    @PrimaryKey
    @Json(name = "url") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "climate") val climate: String,
    @Json(name = "orbital_period") val orbitalPeriod: String,
    @Json(name = "gravity") val gravity: String
)
