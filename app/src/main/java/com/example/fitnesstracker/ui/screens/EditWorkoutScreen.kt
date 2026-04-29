package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.local.Workout

@Composable
fun EditWorkoutScreen(
    workout: Workout,
    onUpdateWorkout: (Workout) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember(workout.id) { mutableStateOf(workout.title) }
    var type by remember(workout.id) { mutableStateOf(workout.type) }
    var duration by remember(workout.id) { mutableStateOf(workout.duration.toString()) }
    var calories by remember(workout.id) { mutableStateOf(workout.calories.toString()) }
    var date by remember(workout.id) { mutableStateOf(workout.date) }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Edit Workout", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(title, { title = it }, label = { Text("Workout title") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(type, { type = it }, label = { Text("Workout type") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it.filter { char -> char.isDigit() } },
            label = { Text("Duration in minutes") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = calories,
            onValueChange = { calories = it.filter { char -> char.isDigit() } },
            label = { Text("Calories") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(date, { date = it }, label = { Text("Date (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())

        if (error.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val durationValue = duration.toIntOrNull()
                val caloriesValue = calories.toIntOrNull()
                if (title.isBlank() || type.isBlank() || date.isBlank()) {
                    error = "Please fill all fields."
                } else if (durationValue == null || durationValue <= 0) {
                    error = "Duration must be a number greater than 0."
                } else if (caloriesValue == null || caloriesValue < 0) {
                    error = "Calories must be a valid number."
                } else {
                    onUpdateWorkout(workout.copy(title = title, type = type, duration = durationValue, calories = caloriesValue, date = date))
                    onCancel()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Update Workout") }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) { Text("Cancel") }
    }
}
