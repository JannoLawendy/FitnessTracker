package com.example.fitnesstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesstracker.data.local.WorkoutDatabase
import com.example.fitnesstracker.data.repository.WorkoutRepository
import com.example.fitnesstracker.ui.navigation.Screen
import com.example.fitnesstracker.ui.screens.AddEditWorkoutScreen
import com.example.fitnesstracker.ui.screens.HistoryScreen
import com.example.fitnesstracker.ui.screens.HomeScreen
import com.example.fitnesstracker.ui.screens.ProgressScreen
import com.example.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.example.fitnesstracker.ui.viewmodel.WorkoutViewModel
import com.example.fitnesstracker.ui.viewmodel.WorkoutViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: WorkoutViewModel by viewModels {
        val database = WorkoutDatabase.getDatabase(applicationContext)
        WorkoutViewModelFactory(WorkoutRepository(database.workoutDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FitnessTrackerTheme {
                val navController = rememberNavController()
                val uiState by viewModel.uiState.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            totalWorkouts = uiState.totalWorkouts,
                            totalMinutes = uiState.totalMinutes,
                            totalCalories = uiState.totalCalories,
                            onAddClick = { navController.navigate(Screen.AddWorkout.route) },
                            onHistoryClick = { navController.navigate(Screen.History.route) },
                            onProgressClick = { navController.navigate(Screen.Progress.route) }
                        )
                    }
                    composable(Screen.AddWorkout.route) {
                        AddEditWorkoutScreen(
                            workoutId = null,
                            viewModel = viewModel,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.History.route) {
                        HistoryScreen(
                            workouts = uiState.workouts,
                            onBackClick = { navController.popBackStack() },
                            onEditClick = { id -> navController.navigate(Screen.EditWorkout.createRoute(id)) },
                            onDeleteClick = { workout -> viewModel.deleteWorkout(workout) }
                        )
                    }
                    composable(Screen.Progress.route) {
                        ProgressScreen(
                            totalWorkouts = uiState.totalWorkouts,
                            totalMinutes = uiState.totalMinutes,
                            totalCalories = uiState.totalCalories,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(
                        route = Screen.EditWorkout.route,
                        arguments = listOf(navArgument("workoutId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val workoutId = backStackEntry.arguments?.getInt("workoutId")
                        AddEditWorkoutScreen(
                            workoutId = workoutId,
                            viewModel = viewModel,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
