package com.example.starwarsplanetsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _splashState = MutableStateFlow(SplashState.NotStarted)
    val splashState: StateFlow<SplashState> = _splashState

    init {
        viewModelScope.launch {
            delay(2000) // 2-second delay
            _splashState.value = SplashState.Completed
        }
    }
}

enum class SplashState {
    NotStarted,
    Completed
}