package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.components.StatCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    totalWorkouts: Int,
    totalMinutes: Int,
    totalCalories: Int,
    onBackClick: () -> Unit
) {
    val averageMinutes = if (totalWorkouts == 0) 0 else totalMinutes / totalWorkouts

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Progress") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatCard("Total Workouts", totalWorkouts.toString())
            StatCard("Total Time", "$totalMinutes minutes")
            StatCard("Total Calories", "$totalCalories calories")
            StatCard("Average Workout Time", "$averageMinutes minutes")
        }
    }
}
