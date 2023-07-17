package com.example.radiusandroidapp.db

import android.content.Context
import androidx.room.Room
import com.example.radiusandroidapp.api.RadiusClient
import com.example.radiusandroidapp.repository.RadiusRepository
import com.example.radiusandroidapp.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideYourRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "radius_database.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideYourDao(database: AppDatabase): RadiusAllProductDao {
        return database.radiusAllProductDao()
    }

    @Singleton
    @Provides
    fun provideRadiusClient(): RadiusClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RadiusClient::class.java)
    }

    @Singleton
    @Provides
    fun provideYourRepository(
        radiusAllProductDao: RadiusAllProductDao,
        radiusClient: RadiusClient
    ): RadiusRepository {
        return RadiusRepository(radiusClient,radiusAllProductDao)
    }
}


