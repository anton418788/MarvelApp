package com.example.marvelapp.ui.components

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDataModel(
    val code: Int,
    val status: String,
    val data: DataModel
)

@Serializable
data class DataModel(
    val results: List<ResultsModel>
)

@Serializable
data class ResultsModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailModel
)

@Serializable
data class ThumbnailModel(
    val path: String,
    val extension: String
)


fun convertUrl(url: String, extension: String): String =
    "${url.replace("http", "https")}.$extension"
