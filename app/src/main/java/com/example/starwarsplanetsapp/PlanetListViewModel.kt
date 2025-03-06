package com.example.starwarsplanetsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class PlanetListViewModel(
    private val repository: PlanetRepository
) : ViewModel() {
    val planets = repository.getPlanetsPagingFlow()
        .cachedIn(viewModelScope)
}