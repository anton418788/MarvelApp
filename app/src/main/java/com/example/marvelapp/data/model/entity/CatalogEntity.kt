package com.example.marvelapp.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatalogEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String
)
