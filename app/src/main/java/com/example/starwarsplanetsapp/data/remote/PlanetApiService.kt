package com.example.starwarsplanetsapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PlanetApiService {
    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetResponse

    @GET
    suspend fun getPlanetDetails(@Url url: String): Planet
}