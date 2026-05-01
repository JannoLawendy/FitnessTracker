package com.example.fitnesstracker.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object AddWorkout : Screen("add_workout")
    data object History : Screen("history")
    data object Progress : Screen("progress")
    data object EditWorkout : Screen("edit_workout/{workoutId}") {
        fun createRoute(workoutId: Int): String = "edit_workout/$workoutId"
    }
}
