package com.example.radiusandroidapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.radiusandroidapp.model.RadiusAllProduct

@Database(entities = [RadiusAllProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun radiusAllProductDao(): RadiusAllProductDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}

