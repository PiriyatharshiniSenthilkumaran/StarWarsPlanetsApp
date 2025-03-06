package com.example.starwarsplanetsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.starwarsplanetsapp.R
import com.example.starwarsplanetsapp.ui.viewmodel.SplashState
import com.example.starwarsplanetsapp.ui.viewmodel.SplashViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = viewModel()
    val splashState by viewModel.splashState.collectAsState()

    LaunchedEffect(splashState) {
        if (splashState == SplashState.Completed) {
            navController.navigate("planets") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.star_wars_logo),
        contentDescription = "Splash Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}
