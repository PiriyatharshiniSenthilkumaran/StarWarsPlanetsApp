package com.example.starwarsplanetsapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = Color.Black,
    secondary = Color.Black,
    tertiary = Color.Black,
    background = Color.Black,
   surface = Color(0xFF121212),
    onSurface = Color.White.copy(alpha = 0.87f),
    onSecondary = Color.White.copy(alpha = 0.70f),
    onBackground = Color.White.copy(alpha = 0.50f),
    onTertiary = Color(0xFFFFD700)
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = Color.Black,
    tertiary = Color.Black,
    background = Color.Black,
    surface = Color(0xFF121212),
    onSurface = Color.White.copy(alpha = 0.87f),
    onSecondary = Color.White.copy(alpha = 0.70f),
    onBackground = Color.White.copy(alpha = 0.50f),
    onTertiary = Color(0xFFFFD700)
)

@Composable
fun StarWarsPlanetsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Set to false to avoid system color overrides
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
