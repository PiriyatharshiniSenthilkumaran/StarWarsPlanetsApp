package com.example.starwarsplanetsapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavController
import com.example.starwarsplanetsapp.ui.screens.SplashScreen
import com.example.starwarsplanetsapp.ui.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import org.mockito.stubbing.OngoingStubbing

@ExperimentalCoroutinesApi
class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val navController = mock(NavController::class.java)

    @Test
    fun `test splash screen displays correctly`() {
        // Set up the composable
        composeTestRule.setContent {
            SplashScreen(navController = navController)
        }

        // Verify that the splash image is displayed
        composeTestRule.onNodeWithContentDescription("Splash Image").assertExists()
    }

    @Test
    fun `test navigation occurs when isAppReady is true`() = runTest {
        // Create a mock ViewModel with isAppReady set to true
        val viewModel = mock(SplashViewModel::class.java)
        val isAppReadyFlow = false
        whenever(viewModel.splashState).thenReturn(isAppReadyFlow = true)

        // Set up the composable
        composeTestRule.setContent {
            SplashScreen(navController = navController)
        }

        // Verify that navigation to "main" screen occurs
        verify(navController).navigate("main") {
            popUpTo("splash") { inclusive = true }
        }
    }
}

private fun <T> OngoingStubbing<T>.thenReturn(isAppReadyFlow: Boolean) {
    TODO("Not yet implemented")
}
