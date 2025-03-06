package com.example.starwarsplanetsapp

sealed class PlanetDetailState {
    object Loading : PlanetDetailState()
    data class Success(val planet: Planet) : PlanetDetailState()
    data class Error(val message: String) : PlanetDetailState()
}