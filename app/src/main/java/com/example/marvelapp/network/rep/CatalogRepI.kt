package com.example.marvelapp.network.rep

import com.example.marvelapp.network.api.MarvelApi
import com.example.marvelapp.ui.components.CatalogDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepI @Inject constructor(
    private val api: MarvelApi
) : CatalogRep {
    override suspend fun getResponseList(): CatalogDataModel = api.getResponseList()

    override suspend fun getCardById(characterId: Int): CatalogDataModel =
        api.getCardById(characterId = characterId)
}

