package com.example.lushlane.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ========== Light Theme Colors ==========
val PrimaryLight = Color(0xFF4CAF50) // Green
val OnPrimaryLight = Color.White
val PrimaryContainerLight = Color(0xFFA5D6A7)
val OnPrimaryContainerLight = Color.Black
val BackgroundLight = Color(0xFFFFFFFF)
val OnBackgroundLight = Color(0xFF000000)
val SurfaceLight = Color(0xFFFFFFFF)
val OnSurfaceLight = Color(0xFF000000)

// ========== Dark Theme Colors ==========
val PrimaryDark = Color(0xFF2E7D32) // Dark Green
val OnPrimaryDark = Color.White
val PrimaryContainerDark = Color(0xFF81C784)
val OnPrimaryContainerDark = Color.Black
val BackgroundDark = Color(0xFF121212)
val OnBackgroundDark = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF1E1E1E)
val OnSurfaceDark = Color(0xFFFFFFFF)

// ========== Light ColorScheme ==========
private val LightColors = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight
)

// ========== Dark ColorScheme ==========
private val DarkColors = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark
)

// ========== Theme Wrapper ==========
@Composable
fun LushLaneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Dynamically switches based on system settings
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,  // You can customize this too if needed
        content = content
    )
}
