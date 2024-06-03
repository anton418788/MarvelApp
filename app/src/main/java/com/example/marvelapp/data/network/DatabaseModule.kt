package com.example.marvelapp.data.network

import android.content.Context
import androidx.room.Room
import com.example.marvelapp.data.model.AppDatabase
import com.example.marvelapp.data.model.CatalogDataAccessObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "cards-database"
        )
            .build()

    @Provides
    fun cardDataAccessObject(database: AppDatabase): CatalogDataAccessObject =
        database.cardDataAccessObject()
}