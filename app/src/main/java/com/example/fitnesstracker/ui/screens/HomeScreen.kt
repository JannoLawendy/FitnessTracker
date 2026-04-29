package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    totalWorkouts: Int,
    totalCalories: Int,
    totalDuration: Int,
    onGoToAdd: () -> Unit,
    onGoToHistory: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Fitness Tracker",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text("Track your workouts and progress easily.")
        Spacer(modifier = Modifier.height(20.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Progress Summary", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Total workouts: $totalWorkouts")
                Text("Total calories: $totalCalories")
                Text("Total time: $totalDuration minutes")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onGoToAdd, modifier = Modifier.fillMaxWidth()) { Text("Add Workout") }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onGoToHistory, modifier = Modifier.fillMaxWidth()) { Text("View History") }
    }
}
