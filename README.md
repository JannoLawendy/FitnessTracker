# Trailblazer - Fitness Tracker

Trailblazer is a final Android fitness tracker project built with Kotlin, Jetpack Compose, Room, Navigation, and MVVM architecture.

## Features

- Home dashboard with total workouts, total minutes, and total calories
- Add workout with validation
- Edit existing workout
- Delete workout with confirmation dialog
- Workout history using LazyColumn
- Progress/statistics screen
- Local data persistence using Room database
- MVVM architecture with ViewModel, Repository, DAO, and Entity
- Jetpack Compose UI with Material 3
- Multi-screen navigation using NavController
- Error handling to prevent crashes from empty or invalid input

## How to Open

1. Unzip the project.
2. Open Android Studio.
3. Click **Open**.
4. Select the folder named **FitnessTracker_FIXED**.
5. Wait for Gradle Sync to finish.
6. Use **JDK 17** in Android Studio Gradle settings.
7. Run the app on an emulator or Android device.

## Important

Do not open only the `app` folder. Open the full project folder.

## Package Name

`com.example.fitnesstracker`

## Architecture

- `data/local` — Room entity, DAO, and database
- `data/repository` — Repository layer
- `ui/screens` — Compose screens
- `ui/components` — Reusable UI components
- `ui/viewmodel` — ViewModel and UI state
- `ui/navigation` — Navigation routes

## Teacher Requirements Covered

- Kotlin + Jetpack Compose
- Multi-screen app
- Navigation
- Data persistence with Room
- CRUD operations
- MVVM architecture
- Clean UI
- Input validation
- Error handling
- Organized project structure
- Reusable components
- README included
