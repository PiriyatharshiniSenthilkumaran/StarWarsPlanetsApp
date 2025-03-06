package com.example.starwarsplanetsapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PlanetListScreen(
    viewModel: PlanetListViewModel,
    navController: NavHostController
) {
    val planets = viewModel.planets.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(planets.itemCount) { index ->
            planets[index]?.let { planet ->
                PlanetCard(
                    planet = planet,
                    navController = navController
                )
            }
        }
    }
}