package com.example.starwarsplanetsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsplanetsapp.data.remote.Planet
import com.example.starwarsplanetsapp.ui.components.PlanetDetailState
import com.example.starwarsplanetsapp.data.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val planetRepository: PlanetRepository // Injecting a repository
) : ViewModel() {

    // MutableStateFlow to manage the state internally
    private val _planetState = MutableStateFlow<PlanetDetailState>(PlanetDetailState.Loading)
    val planetState: StateFlow<PlanetDetailState> = _planetState

    // Function to load planet details
    fun loadPlanetDetails(planetId: String) {

//        if (!isInternetAvailable(context)) {
//            _uiState.value = PlanetListUiState.Error("No internet connection")
//            return
//        }

        viewModelScope.launch {
            _planetState.value = PlanetDetailState.Loading // Set state to Loading

            try {
                // Fetch planet details from the repository
                val planet = planetRepository.getPlanetDetails(planetId)

                // Update state to Success with the fetched planet
                _planetState.value = PlanetDetailState.Success(planet)
            } catch (e: Exception) {
                // Update state to Error if something goes wrong
                _planetState.value = PlanetDetailState.Error("Failed to load planet details: ${e.message}")
            }
        }
    }
}