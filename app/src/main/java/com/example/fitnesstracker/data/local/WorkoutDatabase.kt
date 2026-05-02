


package com.example.fitnesstracker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Main database for the app (stores workouts)
@Database(entities = [WorkoutEntity::class], version = 1)
abstract class WorkoutDatabase : RoomDatabase() {

    // gives access to DAO functions (insert, delete, etc.)
    abstract fun workoutDao(): WorkoutDao

    companion object {

        private var instance: WorkoutDatabase? = null

        // create database only once
        fun getDatabase(context: Context): WorkoutDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutDatabase::class.java,
                    "workout_db"
                ).build()
            }

            return instance!!
        }
    }
}