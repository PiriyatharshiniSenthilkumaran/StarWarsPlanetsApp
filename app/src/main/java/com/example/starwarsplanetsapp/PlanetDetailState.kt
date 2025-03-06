package com.example.starwarsplanetsapp

import com.example.starwarsplanetsapp.data.remote.Planet

sealed class PlanetDetailState {
    object Loading : PlanetDetailState()
    data class Error(val message: String) : PlanetDetailState()
    data class Success(val planet: Planet) : PlanetDetailState()
}