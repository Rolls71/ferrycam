package com.example.ferrycam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DarkGrey15,
    onPrimary = Color.White,
    secondary = DarkGrey09,
    background = Color.Black,
    onBackground = Color.White,
)

private val LightColorPalette = darkColors(
    primary = NotLightBlue,
    onPrimary = Color.White,
    secondary = DarkBlue,
    background = Color.White,
    onBackground = Color.Black,
)

@Composable
fun FerryCamTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = NotLightBlue
    )
    var colors = LightColorPalette

    if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Black
        )
        colors = DarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}