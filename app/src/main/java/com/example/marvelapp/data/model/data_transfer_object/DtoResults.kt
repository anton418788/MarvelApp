package com.example.marvelapp.data.model.data_transfer_object

import kotlinx.serialization.Serializable

@Serializable
data class DtoResults(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: DtoThumbnail
)
