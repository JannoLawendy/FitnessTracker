package com.example.fitnesstracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


// This class represents one workout saved in the Room database.
// Each property becomes a column in the workouts table.
@Entity(tableName = "workouts")
data class WorkoutEntity(
    //creates unique id for each workout
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val durationMinutes: Int,
    val calories: Int,
    val notes: String,
    // The date is saved as milliseconds so it can be sorted and formatted later.
    val dateMillis: Long = System.currentTimeMillis()
)
