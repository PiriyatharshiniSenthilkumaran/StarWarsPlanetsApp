package com.example.starwarsplanetsapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.starwarsplanetsapp.ui.viewmodel.PlanetDetailViewModel
import com.example.starwarsplanetsapp.ui.viewmodel.PlanetListViewModel
import com.example.starwarsplanetsapp.ui.screens.PlanetDetailScreen
import com.example.starwarsplanetsapp.ui.screens.PlanetListScreen
import com.example.starwarsplanetsapp.ui.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("planets") {
            val viewModel = hiltViewModel<PlanetListViewModel>()
            PlanetListScreen(viewModel, navController)
        }
        composable("planetDetail/{url}") { backStackEntry ->
            val viewModel = hiltViewModel<PlanetDetailViewModel>()
            val url = backStackEntry.arguments?.getString("url") ?: ""
            PlanetDetailScreen(viewModel, navController, url)
        }

    }
}