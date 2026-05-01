package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.components.StatCard

@Composable
fun HomeScreen(
    totalWorkouts: Int,
    totalMinutes: Int,
    totalCalories: Int,
    onAddClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onProgressClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Trailblazer",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Track your workouts, history, and progress.",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(title = "Workouts", value = totalWorkouts.toString(), modifier = Modifier.weight(1f))
            StatCard(title = "Minutes", value = totalMinutes.toString(), modifier = Modifier.weight(1f))
        }
        StatCard(title = "Calories Burned", value = totalCalories.toString())

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onAddClick, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.Add, contentDescription = null)
            Text(text = " Add Workout")
        }
        OutlinedButton(onClick = onHistoryClick, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.History, contentDescription = null)
            Text(text = " View History")
        }
        OutlinedButton(onClick = onProgressClick, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.BarChart, contentDescription = null)
            Text(text = " Progress")
        }
    }
}
