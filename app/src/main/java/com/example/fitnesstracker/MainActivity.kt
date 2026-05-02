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

    // This creates the ViewModel and gives it access to the Room database through the repository.
    // I used a factory because the ViewModel needs a constructor parameter.
    private val viewModel: WorkoutViewModel by viewModels {
        val database = WorkoutDatabase.getDatabase(applicationContext)
        WorkoutViewModelFactory(WorkoutRepository(database.workoutDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // FitnessTrackerTheme keeps the colors and Material design consistent in the whole app.
            FitnessTrackerTheme {
                val navController = rememberNavController()
                // collectAsState listens to the ViewModel state and updates the UI automatically.
                val uiState by viewModel.uiState.collectAsState()

                // NavHost controls which screen is currently shown.
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
                    // Screen used to create a new workout. workoutId is null because it is not editing.
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
                    // Edit screen receives the workout id from the route, for example edit_workout/3.
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
