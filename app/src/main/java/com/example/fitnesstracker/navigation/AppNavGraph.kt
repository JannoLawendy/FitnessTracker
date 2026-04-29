package com.example.fitnesstracker.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesstracker.data.local.Workout
import com.example.fitnesstracker.ui.screens.AddWorkoutScreen
import com.example.fitnesstracker.ui.screens.EditWorkoutScreen
import com.example.fitnesstracker.ui.screens.HistoryScreen
import com.example.fitnesstracker.ui.screens.HomeScreen
import com.example.fitnesstracker.viewmodel.WorkoutViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val viewModel: WorkoutViewModel = viewModel()

    val workouts by viewModel.workouts.collectAsState()
    val totalWorkouts by viewModel.totalWorkouts.collectAsState()
    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalDuration by viewModel.totalDuration.collectAsState()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                totalWorkouts = totalWorkouts,
                totalCalories = totalCalories,
                totalDuration = totalDuration,
                onGoToAdd = { navController.navigate("add") },
                onGoToHistory = { navController.navigate("history") }
            )
        }

        composable("add") {
            AddWorkoutScreen(
                onSaveWorkout = { viewModel.addWorkout(it) },
                onCancel = { navController.popBackStack() }
            )
        }

        composable("history") {
            HistoryScreen(
                workouts = workouts,
                onBack = { navController.popBackStack() },
                onEdit = { id -> navController.navigate("edit/$id") },
                onDelete = { viewModel.deleteWorkout(it) }
            )
        }

        composable(
            route = "edit/{workoutId}",
            arguments = listOf(navArgument("workoutId") { type = NavType.IntType })
        ) { backStackEntry ->
            val workoutId = backStackEntry.arguments?.getInt("workoutId") ?: 0
            var workout by remember { mutableStateOf<Workout?>(null) }

            LaunchedEffect(workoutId) {
                workout = viewModel.getWorkoutById(workoutId)
            }

            if (workout == null) {
                Text("Loading workout...")
            } else {
                EditWorkoutScreen(
                    workout = workout!!,
                    onUpdateWorkout = { viewModel.updateWorkout(it) },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}
