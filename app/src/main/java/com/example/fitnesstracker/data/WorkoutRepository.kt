package com.example.fitnesstracker.data

import com.example.fitnesstracker.data.local.Workout
import com.example.fitnesstracker.data.local.WorkoutDao
import kotlinx.coroutines.flow.Flow

class WorkoutRepository(private val dao: WorkoutDao) {
    fun getAllWorkouts(): Flow<List<Workout>> = dao.getAllWorkouts()
    suspend fun getWorkoutById(id: Int): Workout? = dao.getWorkoutById(id)
    suspend fun insertWorkout(workout: Workout) = dao.insertWorkout(workout)
    suspend fun updateWorkout(workout: Workout) = dao.updateWorkout(workout)
    suspend fun deleteWorkout(workout: Workout) = dao.deleteWorkout(workout)
}
