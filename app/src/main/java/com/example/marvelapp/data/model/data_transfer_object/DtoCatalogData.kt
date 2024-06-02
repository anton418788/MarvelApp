package com.example.marvelapp.data.model.data_transfer_object

import kotlinx.serialization.Serializable

@Serializable
data class DtoCatalogData(
    val code: Int,
    val status: String,
    val data: DtoData
)
