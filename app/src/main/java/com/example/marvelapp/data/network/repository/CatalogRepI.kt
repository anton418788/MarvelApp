package com.example.marvelapp.data.network.repository

import android.util.Log
import com.example.marvelapp.data.mapper.CatalogDtoEntityMap
import com.example.marvelapp.data.mapper.CatalogEntityMapper
import com.example.marvelapp.data.model.CatalogDataAccessObject
import com.example.marvelapp.data.model.data_transfer_object.DtoCatalogData
import com.example.marvelapp.data.model.ui.UiResults
import com.example.marvelapp.data.network.api.MarvelApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepI @Inject constructor(
    private val api: MarvelApi,
    private val dao: CatalogDataAccessObject,
    private val dtoToEntityMapper: CatalogDtoEntityMap,
    private val entityToUiMapper: CatalogEntityMapper
) : CatalogRep {

    override suspend fun getCardsList(): DtoCatalogData {
        try {
            val cardsCard = api.getCardsList()
            val entities = dtoToEntityMapper.map(cardsCard)
            entities.forEach { dao.insert(it) }
            return cardsCard
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl", "Fatal error! Cant getting cards list - check ur internet connection", e)
            throw e
        }
    }

    override suspend fun getCardById(characterId: Int): DtoCatalogData {
        try {
            val cardDto = api.getCardById(characterId)
            val entity = dtoToEntityMapper.map(cardDto)
            entity.forEach { dao.insert(it) }
            return cardDto
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl", "Fatal error! Cant getting card by ID: $characterId", e)
            throw e
        }
    }

    override suspend fun getCardById(): List<UiResults> {
        try {
            val entities = dao.getAllEntity()
            return entityToUiMapper.map(entities)
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl", "Fatal error! Cant getting local cards list", e)
            throw e
        }
    }

    override suspend fun getLocalCard(characterId: Int): UiResults {
        try {
            val entity = dao.cardById(characterId)
            return entity.let { entityToUiMapper.map(it) }
        } catch (e: Exception) {
            Log.e("MainRepositoryImpl", "Fatal error! Cant getting local card by ID: $characterId", e)
            throw e
        }
    }
}