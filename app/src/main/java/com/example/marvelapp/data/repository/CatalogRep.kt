package com.example.marvelapp.data.repository

import com.example.marvelapp.data.model.data_transfer_object.DtoCatalogData
import com.example.marvelapp.data.model.ui.UiResults


interface CatalogRep {
    suspend fun getCardsList(): DtoCatalogData

    suspend fun getCardById(characterId: Int): DtoCatalogData

    suspend fun getCardById(): List<UiResults>

    suspend fun getLocalCard(characterId: Int): UiResults
}