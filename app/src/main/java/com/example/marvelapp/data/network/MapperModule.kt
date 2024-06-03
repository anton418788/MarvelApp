package com.example.marvelapp.data.network

import com.example.marvelapp.data.mapper.CatalogDtoEntityMap
import com.example.marvelapp.data.mapper.CatalogEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideDtoToEntityMapper(): CatalogDtoEntityMap {
        return CatalogDtoEntityMap()
    }

    @Provides
    @Singleton
    fun provideEntityToUiMapper(): CatalogEntityMapper {
        return CatalogEntityMapper()
    }
}