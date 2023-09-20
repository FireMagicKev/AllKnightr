package com.example.allknightrapp.uicompose.util

object TextUtils {
    fun isEmailValid(email: String): Boolean {
        // Implement email validation logic here
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}