package com.example.jornadaandroid2023.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HintEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hintDao(): HintDao

    companion object{
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
           return instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app-database"
            ).build().also{ instance = it}
        }
    }
}