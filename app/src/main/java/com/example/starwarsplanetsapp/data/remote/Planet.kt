package com.example.starwarsplanetsapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Planet(
    @Json(name = "url") val id: String,  // URL from API
    @Json(name = "name") val name: String,
    @Json(name = "climate") val climate: String,
    @Json(name = "orbital_period") val orbitalPeriod: String,
    @Json(name = "gravity") val gravity: String
)
