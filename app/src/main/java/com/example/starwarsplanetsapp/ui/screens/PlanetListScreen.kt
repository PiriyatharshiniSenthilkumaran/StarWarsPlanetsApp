package com.example.starwarsplanetsapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.starwarsplanetsapp.R
import com.example.starwarsplanetsapp.ui.viewmodel.PlanetListViewModel
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetListScreen(
    viewModel: PlanetListViewModel,
    navController: NavHostController
) {
    val planets = viewModel.planets.collectAsLazyPagingItems()

    val backgroundImage: Painter = painterResource(id = R.drawable.screen_background)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Star Wars Planets",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 40.sp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            Image(
                painter = backgroundImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(planets.itemCount) { index ->
                    planets[index]?.let { planet ->
                        PlanetCard(
                            planet = planet,
                            onClick = {
                                val encodedUrl = URLEncoder.encode(planet.id, "UTF-8")
                                Log.d("ccpp PlanetListScreen", "Encoded URL: $encodedUrl")
                                navController.navigate("planetDetail/$encodedUrl")
                            }
                        )
                        Log.v("ccpp", "$planet")
                    }
                }
            }
        }
    }
}

