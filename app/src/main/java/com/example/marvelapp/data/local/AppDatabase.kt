package com.example.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelapp.data.model.entity.CatalogEntity

@Database(entities = [CatalogEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDataAccessObject() : CatalogDataAccessObject
}