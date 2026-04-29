package com.example.fitnesstracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FitnessColors = lightColorScheme(
    primary = Color(0xFF006D5B),
    secondary = Color(0xFF4E635E),
    background = Color(0xFFF5FBF8),
    surface = Color.White
)

@Composable
fun FitnessTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FitnessColors,
        content = content
    )
}
