package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.viewmodel.WorkoutViewModel



// This screen is used for both adding and editing a workout.
// If workoutId is null, it adds a new workout. If workoutId has a value, it edits that workout.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditWorkoutScreen(
    workoutId: Int?,
    viewModel: WorkoutViewModel,
    onBackClick: () -> Unit
) {
    // remember keeps the typed values on the screen while Compose recomposes the UI.
    var type by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // When editing, this loads the selected workout and fills the form fields.
    LaunchedEffect(workoutId) {
        if (workoutId != null) {
            val workout = viewModel.getWorkoutById(workoutId)
            if (workout != null) {
                type = workout.type
                duration = workout.durationMinutes.toString()
                calories = workout.calories.toString()
                notes = workout.notes
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (workoutId == null) "Add Workout" else "Edit Workout") },
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            OutlinedTextField(
                value = type,
                onValueChange = { type = it },
                label = { Text("Workout type") },
                placeholder = { Text("Running, gym, cycling...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = duration,
                onValueChange = { duration = it.filter { char -> char.isDigit() } },
                label = { Text("Duration in minutes") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = calories,
                onValueChange = { calories = it.filter { char -> char.isDigit() } },
                label = { Text("Calories burned") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            // When editing, this loads the selected workout and fills the form fields.
            if (errorMessage != null) {
                Text(text = errorMessage ?: "", color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = {
                    errorMessage = if (workoutId == null) {
                        viewModel.addWorkout(type, duration, calories, notes)
                    } else {
                        viewModel.updateWorkout(workoutId, type, duration, calories, notes)
                    }
                    if (errorMessage == null) onBackClick()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (workoutId == null) "Save Workout" else "Update Workout")
            }
        }
    }
}
