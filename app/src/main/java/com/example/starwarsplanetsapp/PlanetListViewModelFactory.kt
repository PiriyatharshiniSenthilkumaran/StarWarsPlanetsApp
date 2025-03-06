package com.example.starwarsplanetsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlanetListViewModelFactory(private val repository: PlanetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanetListViewModel(repository) as T
    }
}

class PlanetDetailViewModelFactory(private val repository: PlanetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanetDetailViewModel(repository) as T
    }
}