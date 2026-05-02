package com.example.fitnesstracker.ui.navigation


// This sealed class keeps all navigation route names in one place.
// It helps avoid spelling mistakes when moving between screens.
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object AddWorkout : Screen("add_workout")
    data object History : Screen("history")
    data object Progress : Screen("progress")
    data object EditWorkout : Screen("edit_workout/{workoutId}") {
        fun createRoute(workoutId: Int): String = "edit_workout/$workoutId"
    }
}
