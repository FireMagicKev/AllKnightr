package com.example.allknightrapp.util

sealed class State<out R> {
    object Loading: State<Nothing>()
    data class Success<T>( val value: T): State<T>()
    data class Error(val exception: Exception): State<Nothing>()
}