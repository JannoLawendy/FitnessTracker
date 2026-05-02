package com.example.fitnesstracker.data.repository

import com.example.fitnesstracker.data.local.WorkoutDao
import com.example.fitnesstracker.data.local.WorkoutEntity
import kotlinx.coroutines.flow.Flow

// Repository separates the ViewModel from the database code.
// The UI talks to the ViewModel, the ViewModel talks to this repository, and this repository talks to Room.
class WorkoutRepository(private val workoutDao: WorkoutDao) {
    val workouts: Flow<List<WorkoutEntity>> = workoutDao.getAllWorkouts()

    suspend fun getWorkoutById(id: Int): WorkoutEntity? = workoutDao.getWorkoutById(id)

    suspend fun addWorkout(workout: WorkoutEntity) = workoutDao.insertWorkout(workout)

    suspend fun updateWorkout(workout: WorkoutEntity) = workoutDao.updateWorkout(workout)

    suspend fun deleteWorkout(workout: WorkoutEntity) = workoutDao.deleteWorkout(workout)

    suspend fun deleteAllWorkouts() = workoutDao.deleteAllWorkouts()
}
