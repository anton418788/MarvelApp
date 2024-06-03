package com.example.marvelapp.data.model.data_transfer_object

import kotlinx.serialization.Serializable

@Serializable
data class DtoThumbnail(
    val path: String,
    val extension: String
)