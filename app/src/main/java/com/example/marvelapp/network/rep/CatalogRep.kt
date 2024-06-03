package com.example.marvelapp.network.rep
import com.example.marvelapp.ui.components.CatalogDataModel

interface CatalogRep {
    suspend fun getResponseList(): CatalogDataModel

    suspend fun getCardById(characterId: Int): CatalogDataModel
}