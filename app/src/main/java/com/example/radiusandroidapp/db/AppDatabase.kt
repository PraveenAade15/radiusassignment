package com.example.radiusandroidapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.radiusandroidapp.model.RadiusAllProduct

@Database(entities = [RadiusAllProduct::class], version = 1, exportSchema = false)
@JvmSuppressWildcards
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun radiusAllProductDao(): RadiusAllProductDao
}


