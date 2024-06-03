package com.example.marvelapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapp.data.model.entity.CatalogEntity

@Dao
interface CatalogDataAccessObject {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cards: CatalogEntity)

    @Query("SELECT * FROM CatalogEntity")
    suspend fun getAllEntity() : List<CatalogEntity>

    @Query("SELECT * FROM CatalogEntity WHERE id = :id")
    suspend fun cardById(id: Int) : CatalogEntity
}