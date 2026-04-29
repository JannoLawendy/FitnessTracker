# Fitness Tracker App

Package name: `com.example.fitnesstracker`

## Overview
Fitness Tracker is an Android application built with Kotlin and Jetpack Compose. It helps users add workouts, view workout history, edit workouts, delete workouts, and see progress statistics.

## Features
- Add workout
- Edit workout
- Delete workout
- View workout history
- Track total workouts, calories, and duration
- Room database persistence
- MVVM architecture
- Jetpack Compose UI
- Navigation with NavController

## How to Run
1. Open Android Studio.
2. Click Open.
3. Select this project folder.
4. Wait for Gradle Sync.
5. Run the app on an emulator or Android phone.

## Project Structure
- data/local: Room entity, DAO, database
- data: repository
- viewmodel: app logic and state
- ui/screens: Compose screens
- ui/components: reusable UI components
- navigation: NavHost and routes
