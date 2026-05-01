package com.example.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.local.WorkoutEntity
import com.example.fitnesstracker.ui.components.WorkoutCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    workouts: List<WorkoutEntity>,
    onBackClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (WorkoutEntity) -> Unit
) {
    var workoutToDelete by remember { mutableStateOf<WorkoutEntity?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workout History") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (workouts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("No workouts yet. Add your first workout!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(workouts, key = { it.id }) { workout ->
                    WorkoutCard(
                        workout = workout,
                        onEditClick = { onEditClick(workout.id) },
                        onDeleteClick = { workoutToDelete = workout }
                    )
                }
            }
        }
    }

    if (workoutToDelete != null) {
        AlertDialog(
            onDismissRequest = { workoutToDelete = null },
            title = { Text("Delete workout?") },
            text = { Text("This action will remove the workout from your history.") },
            confirmButton = {
                TextButton(onClick = {
                    workoutToDelete?.let(onDeleteClick)
                    workoutToDelete = null
                }) { Text("Delete") }
            },
            dismissButton = {
                TextButton(onClick = { workoutToDelete = null }) { Text("Cancel") }
            }
        )
    }
}
