package com.example.fitnesstracker.data;

import com.example.fitnesstracker.data.local.Workout;
import com.example.fitnesstracker.data.local.WorkoutDao;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bJ\u0018\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/fitnesstracker/data/WorkoutRepository;", "", "dao", "Lcom/example/fitnesstracker/data/local/WorkoutDao;", "(Lcom/example/fitnesstracker/data/local/WorkoutDao;)V", "deleteWorkout", "", "workout", "Lcom/example/fitnesstracker/data/local/Workout;", "(Lcom/example/fitnesstracker/data/local/Workout;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWorkouts", "Lkotlinx/coroutines/flow/Flow;", "", "getWorkoutById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWorkout", "updateWorkout", "app_debug"})
public final class WorkoutRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.fitnesstracker.data.local.WorkoutDao dao = null;
    
    public WorkoutRepository(@org.jetbrains.annotations.NotNull()
    com.example.fitnesstracker.data.local.WorkoutDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.fitnesstracker.data.local.Workout>> getAllWorkouts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getWorkoutById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.fitnesstracker.data.local.Workout> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertWorkout(@org.jetbrains.annotations.NotNull()
    com.example.fitnesstracker.data.local.Workout workout, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateWorkout(@org.jetbrains.annotations.NotNull()
    com.example.fitnesstracker.data.local.Workout workout, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteWorkout(@org.jetbrains.annotations.NotNull()
    com.example.fitnesstracker.data.local.Workout workout, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}