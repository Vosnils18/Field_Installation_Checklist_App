package eu.foxbms.installationchecklist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = FoxOrange,
    primaryContainer = FoxDarkGray,
    onPrimary = FoxWhite,
    secondary = FoxDarkGray,
    secondaryContainer = FoxLightGray,
    onSecondary = FoxWhite,
    surface = FoxWhite,
    onSurface = FoxBlack,
    error = FoxError,
    onError = FoxOnError
)

private val DarkColorScheme = darkColorScheme(
    primary = FoxOrange,
    primaryContainer = FoxDarkGray,
    onPrimary = FoxWhite,
    secondary = FoxDarkGray,
    secondaryContainer = FoxLightGray,
    onSecondary = FoxWhite,
    surface = FoxBlack,
    onSurface = FoxWhite,
    error = FoxError,
    onError = FoxOnError
)

@Composable
fun FoxBMSChecklistTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
