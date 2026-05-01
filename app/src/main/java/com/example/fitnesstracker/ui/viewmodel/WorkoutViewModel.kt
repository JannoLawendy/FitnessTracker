package com.example.fitnesstracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.local.WorkoutEntity
import com.example.fitnesstracker.data.repository.WorkoutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class WorkoutUiState(
    val workouts: List<WorkoutEntity> = emptyList(),
    val totalWorkouts: Int = 0,
    val totalMinutes: Int = 0,
    val totalCalories: Int = 0
)

class WorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {
    val uiState: StateFlow<WorkoutUiState> = repository.workouts.map { workouts ->
        WorkoutUiState(
            workouts = workouts,
            totalWorkouts = workouts.size,
            totalMinutes = workouts.sumOf { it.durationMinutes },
            totalCalories = workouts.sumOf { it.calories }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WorkoutUiState()
    )

    fun addWorkout(type: String, duration: String, calories: String, notes: String): String? {
        val validationError = validateWorkout(type, duration, calories)
        if (validationError != null) return validationError

        viewModelScope.launch {
            repository.addWorkout(
                WorkoutEntity(
                    type = type.trim(),
                    durationMinutes = duration.toInt(),
                    calories = calories.toInt(),
                    notes = notes.trim()
                )
            )
        }
        return null
    }

    fun updateWorkout(id: Int, type: String, duration: String, calories: String, notes: String): String? {
        val validationError = validateWorkout(type, duration, calories)
        if (validationError != null) return validationError

        viewModelScope.launch {
            val currentWorkout = repository.getWorkoutById(id)
            if (currentWorkout != null) {
                repository.updateWorkout(
                    currentWorkout.copy(
                        type = type.trim(),
                        durationMinutes = duration.toInt(),
                        calories = calories.toInt(),
                        notes = notes.trim()
                    )
                )
            }
        }
        return null
    }

    fun deleteWorkout(workout: WorkoutEntity) {
        viewModelScope.launch { repository.deleteWorkout(workout) }
    }

    fun deleteAllWorkouts() {
        viewModelScope.launch { repository.deleteAllWorkouts() }
    }

    suspend fun getWorkoutById(id: Int): WorkoutEntity? = repository.getWorkoutById(id)

    private fun validateWorkout(type: String, duration: String, calories: String): String? {
        if (type.trim().isEmpty()) return "Workout type cannot be empty."
        val durationNumber = duration.toIntOrNull()
        val caloriesNumber = calories.toIntOrNull()
        if (durationNumber == null || durationNumber <= 0) return "Duration must be a number greater than 0."
        if (caloriesNumber == null || caloriesNumber < 0) return "Calories must be 0 or more."
        return null
    }
}

class WorkoutViewModelFactory(private val repository: WorkoutRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
