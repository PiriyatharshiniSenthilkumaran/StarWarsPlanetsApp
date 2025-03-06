package com.example.starwarsplanetsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsplanetsapp.data.repository.PlanetRepository


class PlanetListViewModelFactory(
    private val repository: PlanetRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanetListViewModel::class.java)) {
            return PlanetListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}