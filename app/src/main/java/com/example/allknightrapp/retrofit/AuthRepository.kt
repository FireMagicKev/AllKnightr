package com.example.allknightrapp.retrofit

import com.example.allknightrapp.di.NetworkModule.clientId
import com.example.allknightrapp.di.NetworkModule.clientSecret
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(@Named("Twitch") retrofit: Retrofit) {
    val igdbService = retrofit.create(IGDBService::class.java)

    fun getAccessToken(): Flow<Result<String>> = flow {
        val response = try {
            igdbService.getAccessToken(
                clientId,
                clientSecret,
            )
        } catch (e: Exception) {
            emit(Result.failure(e))
            return@flow
        }

        val body = response.body()
        if (response.isSuccessful && body != null) {
            emit(Result.success(body.accessToken))
        } else {
            emit(Result.failure(IllegalStateException()))
        }
    }
}
