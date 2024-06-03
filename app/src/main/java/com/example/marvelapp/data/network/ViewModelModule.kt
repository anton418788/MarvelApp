package com.example.marvelapp.data.network

import com.example.marvelapp.data.model.CatalogDataAccessObject
import com.example.marvelapp.data.mapper.CatalogDtoEntityMap
import com.example.marvelapp.data.mapper.CatalogEntityMapper
import com.example.marvelapp.data.network.repository.CatalogRep
import com.example.marvelapp.ui.screens.detail.DetailVM
import com.example.marvelapp.ui.screens.main.CatalogVM
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCatalogVM(
        catalogRep: CatalogRep,
        catalogDataAccessObject: CatalogDataAccessObject,
        dtoToEntityMapper: CatalogDtoEntityMap,
        entityToUiMapper: CatalogEntityMapper
    ): CatalogVM {
        return CatalogVM(catalogRep, catalogDataAccessObject, dtoToEntityMapper, entityToUiMapper)
    }

    @Provides
    @ViewModelScoped
    fun provideDetailVM(
        catalogRep: CatalogRep,
        catalogDataAccessObject: CatalogDataAccessObject,
        dtoToEntityMapper: CatalogDtoEntityMap,
        entityToUiMapper: CatalogEntityMapper
    ): DetailVM {
        return DetailVM(catalogRep, catalogDataAccessObject, dtoToEntityMapper, entityToUiMapper)
    }
}