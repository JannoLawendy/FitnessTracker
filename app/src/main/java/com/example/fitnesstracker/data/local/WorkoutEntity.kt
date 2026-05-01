package com.example.fitnesstracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val durationMinutes: Int,
    val calories: Int,
    val notes: String,
    val dateMillis: Long = System.currentTimeMillis()
)
