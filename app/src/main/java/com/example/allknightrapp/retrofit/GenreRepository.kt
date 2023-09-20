package com.example.allknightrapp.retrofit

import com.example.allknightrapp.util.AwaitResult
import com.example.allknightrapp.util.State
import com.example.allknightrapp.util.awaitResult
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(
    @Named("Igdb") igdbRetrofit: Retrofit,
    @Named("Twitch") val twitchRetrofit: Retrofit,
) {

    val igdbService = igdbRetrofit.create(IGDBService::class.java)

    fun getGenre() = flow {
        when (val accessTokenResponse = igdbService.getAccessToken().awaitResult()) {
            is AwaitResult.Ok -> {
                val accessToken = accessTokenResponse.value.accessToken
                igdbService.getGenres(accessToken)
                when (val result = igdbService.getGenres(accessToken).awaitResult()) {
                    is AwaitResult.Ok -> {
                        emit(State.Success(result.value))
                    }
                    is AwaitResult.Error -> {
                        emit(State.Error(result.exception))
                    }
                }
            }
            is AwaitResult.Error -> {
                emit(State.Error(accessTokenResponse.exception))
            }
        }
    }
}