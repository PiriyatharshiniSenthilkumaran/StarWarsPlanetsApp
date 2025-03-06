package com.example.starwarsplanetsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlanetDetailViewModel(
    private val repository: PlanetRepository
) : ViewModel() {
    private val _state = MutableStateFlow<PlanetDetailState>(PlanetDetailState.Loading)
    val state: StateFlow<PlanetDetailState> = _state

    fun loadPlanetDetails(url: String) {
        viewModelScope.launch {
            _state.value = PlanetDetailState.Loading
            try {
                val planet = repository.getPlanetDetails(url)
                _state.value = PlanetDetailState.Success(planet)
            } catch (e: Exception) {
                _state.value = PlanetDetailState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
