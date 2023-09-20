package com.example.allknightrapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val clientId = "kur5s0qeeiiemazrf4ahlv271f1f96"
    const val clientSecret = "3nukpku7tcgfhzxvh8t5kdx52d8uv0"

    @Provides
    @Singleton
    fun providesJson(): Json {
        return Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Named("Twitch")
    @Singleton
    fun providesTwitchRetrofit(json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://id.twitch.tv")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Named("Igdb")
    @Singleton
    fun providesIgdbRetrofit(json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://api.igdb.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }
}