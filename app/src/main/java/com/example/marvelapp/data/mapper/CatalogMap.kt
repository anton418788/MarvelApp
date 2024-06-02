package com.example.marvelapp.data.mapper

import com.example.marvelapp.data.model.data_transfer_object.DtoCatalogData
import com.example.marvelapp.data.model.data_transfer_object.DtoResults
import com.example.marvelapp.data.model.entity.CatalogEntity
import com.example.marvelapp.data.model.ui.UiResults

class CatalogMap {
    fun map(dto: DtoResults): UiResults {
        return UiResults(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            thumbnail = convertUrl(
                url = dto.thumbnail.path,
                extension = dto.thumbnail.extension
            )
        )
    }
}

class CatalogEntityMapper {
    fun map(entity: CatalogEntity): UiResults {
        return UiResults(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            thumbnail = entity.thumbnail
        )
    }

    fun map(entities: List<CatalogEntity>): List<UiResults> {
        return entities.map { map(it) }
    }
}

class CatalogDtoEntityMap {
    fun map(dto: DtoCatalogData): List<CatalogEntity> {
        return dto.data.results.map {
            CatalogEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                thumbnail = convertUrl(
                    url = it.thumbnail.path,
                    extension = it.thumbnail.extension
                )
            )
        }
    }
}

fun convertUrl(url: String, extension: String): String =
    "${url.replace("http", "https")}.$extension"