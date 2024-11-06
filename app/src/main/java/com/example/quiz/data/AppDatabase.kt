package com.example.quiz.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PlayerScore::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerScoreDao(): PlayerScoreDao
}
