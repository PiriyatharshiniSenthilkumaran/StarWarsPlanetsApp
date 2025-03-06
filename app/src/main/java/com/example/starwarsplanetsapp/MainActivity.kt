package com.example.starwarsplanetsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.starwarsplanetsapp.ui.theme.StarWarsPlanetsAppTheme
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val database = remember { AppDatabase.create(applicationContext) }
            val apiService = remember {
                Retrofit.Builder()
                    .baseUrl("https://swapi.dev/api/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(PlanetApiService::class.java)
            }
            val repository = remember { PlanetRepository(apiService, database) }

            StarWarsPlanetsAppTheme {
                AppNavigation(repository)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarWarsPlanetsAppTheme {
        Greeting("Android")
    }
}