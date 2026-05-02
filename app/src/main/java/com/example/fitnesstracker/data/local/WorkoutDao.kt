package com.example.fitnesstracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



// DAO means Data Access Object.
// This file contains the database actions for workouts: read, add, edit, and delete.
@Dao
interface WorkoutDao {

    // used flow to makes the list update automatically when the database changes.
    @Query("SELECT * FROM workouts ORDER BY dateMillis DESC")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>
    // Used when opening the edit screen, so the app can load the selected workout.
    @Query("SELECT * FROM workouts WHERE id = :id LIMIT 1")
    suspend fun getWorkoutById(id: Int): WorkoutEntity?

    // Adds a new workout. REPLACE avoids duplicate primary key conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutEntity)
    // Updates an existing workout.
    @Update
    suspend fun updateWorkout(workout: WorkoutEntity)
    // Deletes one selected workout.
    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)
    // Deletes all workouts. This is available in the data layer if needed later.
    @Query("DELETE FROM workouts")
    suspend fun deleteAllWorkouts()
}
