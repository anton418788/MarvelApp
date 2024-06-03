package com.example.marvelapp.network.api

import com.example.marvelapp.ui.components.CatalogDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getResponseList(
        @Query("limit") limit: Long = 7
    ): CatalogDataModel

    @GET("v1/public/characters/{characterId}")
    suspend fun getCardById(
        @Path("characterId") characterId: Int
    ): CatalogDataModel
}