package com.example.starwarsplanetsapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(repository: PlanetRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("planets") {
            PlanetListScreen(
                viewModel = viewModel(factory = PlanetListViewModelFactory(repository)),
                navController = navController
            )
        }
        composable("planet/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            PlanetDetailScreen(
                viewModel = viewModel(factory = PlanetDetailViewModelFactory(repository)),
                navController = navController,
                url = url
            )
        }
    }
}