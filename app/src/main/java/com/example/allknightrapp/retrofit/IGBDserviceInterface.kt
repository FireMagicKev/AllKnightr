package com.example.allknightrapp.retrofit

import com.example.allknightrapp.di.NetworkModule
import com.example.allknightrapp.models.GenresResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IGDBService {
    @Headers("Client-ID: kur5s0qeeiiemazrf4ahlv271f1f96", "Authorization: Bearer {accessToken}")
    @POST("/v4/genres")
    suspend fun getGenres(
        @Path("accessToken") accessToken: String,
        @Body fields: String = "fields *; limit 100;"
    ): Response<List<GenresResponseItem>>

    @POST("/oauth2/token")
    suspend fun getAccessToken(
        @Query("client_id") clientId: String = NetworkModule.clientId,
        @Query("client_secret") clientSecret: String = NetworkModule.clientSecret,
        @Query("grant_type") grantType: String = "client_credentials",
    ): Response<AuthTokenResponse>
}
