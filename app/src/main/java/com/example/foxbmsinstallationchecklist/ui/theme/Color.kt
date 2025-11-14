package com.example.foxbmsinstallationchecklist.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

// Fox BMS Brand Colors
val FoxOrange = Color(0xFFF47C20)  // Main orange from logo
val FoxDarkGray = Color(0xFF333333)  // Dark gray from logo
val FoxLightGray = Color(0xFFF5F5F5)
val FoxMediumGray = Color(0xFFE0E0E0)
val FoxSuccessGreen = Color(0xFF4CAF50)
val FoxErrorRed = Color(0xFFF44336)

// Color schemes
val DarkColorScheme = darkColorScheme(
    primary = FoxOrange,
    secondary = Color(0xFF03DAC6),
    tertiary = FoxLightGray,
    background = FoxDarkGray,
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = FoxErrorRed,
    onError = Color.White
)

val LightColorScheme = lightColorScheme(
    primary = FoxOrange,
    secondary = Color(0xFF03DAC6),
    tertiary = FoxDarkGray,
    background = FoxLightGray,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = FoxErrorRed,
    onError = Color.White
)
