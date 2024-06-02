package com.example.marvelapp.network.api

import com.example.marvelapp.data.model.data_transfer_object.DtoCatalogData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getCardsList(
        @Query("limit") limit: Long = 7
    ): DtoCatalogData

    @GET("v1/public/characters/{characterId}")
    suspend fun getCardById(
        @Path("characterId") characterId: Int
    ): DtoCatalogData
}


