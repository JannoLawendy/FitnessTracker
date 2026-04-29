package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.local.Workout
import com.example.fitnesstracker.ui.components.WorkoutCard

@Composable
fun HistoryScreen(
    workouts: List<Workout>,
    onBack: () -> Unit,
    onEdit: (Int) -> Unit,
    onDelete: (Workout) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Workout History", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        if (workouts.isEmpty()) {
            Text("No workouts yet. Add your first workout from the home screen.")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(workouts, key = { it.id }) { workout ->
                    WorkoutCard(workout = workout, onEdit = { onEdit(workout.id) }, onDelete = { onDelete(workout) })
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Back Home") }
    }
}
