package com.example.fitnesstracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.WorkoutRepository
import com.example.fitnesstracker.data.local.Workout
import com.example.fitnesstracker.data.local.WorkoutDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WorkoutRepository

    init {
        val dao = WorkoutDatabase.getDatabase(application).workoutDao()
        repository = WorkoutRepository(dao)
    }

    val workouts: StateFlow<List<Workout>> = repository.getAllWorkouts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalWorkouts: StateFlow<Int> = repository.getAllWorkouts()
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val totalCalories: StateFlow<Int> = repository.getAllWorkouts()
        .map { list -> list.sumOf { it.calories } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val totalDuration: StateFlow<Int> = repository.getAllWorkouts()
        .map { list -> list.sumOf { it.duration } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addWorkout(workout: Workout) = viewModelScope.launch { repository.insertWorkout(workout) }
    fun updateWorkout(workout: Workout) = viewModelScope.launch { repository.updateWorkout(workout) }
    fun deleteWorkout(workout: Workout) = viewModelScope.launch { repository.deleteWorkout(workout) }
    suspend fun getWorkoutById(id: Int): Workout? = repository.getWorkoutById(id)
}
