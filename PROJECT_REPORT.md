# One-Page Project Report

## Project Title
Trailblazer - Fitness Tracker

## Team Members
Janno Lawendy - UI/UX and Frontend using Jetpack Compose  
Agnesa Verenceanu - Backend and Data using Room database and app logic

## Project Overview
Trailblazer is a fitness tracking mobile application that helps users record workouts and follow their progress. The app is made for users who want a simple way to save workout information such as exercise type, duration, calories, and notes.

## Main Features
The app allows the user to add, edit, delete, and view workouts. It also includes a home dashboard, workout history screen, and progress screen. The progress screen shows total workouts, total workout time, calories burned, and average workout time.

## Technical Implementation
The project uses Kotlin and Jetpack Compose for the user interface. It uses Navigation Compose for moving between screens. Room database is used for local data storage. The app follows the MVVM architecture with separation between UI, ViewModel, repository, and database layers.

## Validation and Error Handling
The app prevents users from saving invalid workouts. Workout type cannot be empty, duration must be greater than zero, and calories must be zero or more. A confirmation dialog is also shown before deleting a workout.

## Future Improvements
Future improvements could include workout reminders, login system, cloud synchronization, workout categories, and personalized workout suggestions.
