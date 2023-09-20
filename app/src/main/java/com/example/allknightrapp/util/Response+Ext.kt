package com.example.allknightrapp.util

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.awaitResult(): AwaitResult<T> {
    return try {
        if (isSuccessful) {
            val body = body()
            if (body != null) {
                AwaitResult.Ok(body, raw())
            } else {
                AwaitResult.Error(NullPointerException("Response body is null"), raw(), errorBody()?.string())
            }
        } else {
            AwaitResult.Error(HttpException(this), raw(), errorBody()?.string())
        }
    } catch (e: Exception) {
        AwaitResult.Error(e, raw(), errorBody()?.string())
    }
}

val <T> Response<T>.value: T
    get() = try {
        body() as T
    } catch (e: Exception) {
        throw HttpException(this)
    }