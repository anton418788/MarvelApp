package com.example.marvelapp.network

import android.util.Log
import com.example.marvelapp.data.local.CatalogDataAccessObject
import com.example.marvelapp.data.mapper.CatalogDtoEntityMap
import com.example.marvelapp.data.mapper.CatalogEntityMapper
import com.example.marvelapp.data.repository.CatalogRep
import com.example.marvelapp.data.repository.CatalogRepI
import com.example.marvelapp.network.api.MarvelApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitService {

    @Provides
    fun provideOkHttpClient(constants: Constants): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .addInterceptor(constants)
            .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Log.d("OkHttp", message)
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        api: MarvelApi,
        dao: CatalogDataAccessObject,
        dtoToEntityMapper: CatalogDtoEntityMap,
        entityToUiMapper: CatalogEntityMapper)
    : CatalogRep {
        return CatalogRepI(api, dao, dtoToEntityMapper, entityToUiMapper)
    }

    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }
}