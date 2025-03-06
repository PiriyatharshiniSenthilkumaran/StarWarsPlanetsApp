package com.example.starwarsplanetsapp
import com.example.starwarsplanetsapp.ui.viewmodel.SplashState
import com.example.starwarsplanetsapp.ui.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule() // Custom rule for coroutine testing

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setup() {
        viewModel = SplashViewModel()
    }

    @Test
    fun `test isAppReady is updated after loading`() = runTest {
        // Initially, isAppReady should be false
        assertFalse(viewModel.splashState.value)

        // Advance time to simulate the loading process
        advanceTimeBy(1000) // Simulate 1 second delay

        // After loading, isAppReady should be true
        assertTrue(viewModel.splashState.value)
    }

    private fun assertTrue(value: SplashState) {
        TODO("Not yet implemented")
    }

    private fun assertFalse(value: SplashState) {
        TODO("Not yet implemented")
    }
}