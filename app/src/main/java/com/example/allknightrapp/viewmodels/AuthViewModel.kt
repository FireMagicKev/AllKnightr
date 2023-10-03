package com.example.allknightrapp.viewmodels

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allknightrapp.auth.AuthManager
import com.example.allknightrapp.auth.AuthState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val authManager: AuthManager
) : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            authManager.getAccessToken()
        }
    }

    fun performLogin(email: String, password: String) {
        authManager.performLogin(email, password)
    }

    private fun performRegistration(email: String, password: String) {
        _authState.value = authState.value.copy(loading = true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _authState.value = authState.value.copy(loading = false)
                if (task.isSuccessful) {
                    _authState.value = authState.value.copy(message = "Login successful")
                } else {
                    _authState.value = authState.value.copy(message = "Login failed")
                }
            }
    }

    private var confirmPassword by mutableStateOf("")
    private var _passwordsMatch = MutableStateFlow(true)
    val passwordsMatch: StateFlow<Boolean> = _passwordsMatch

    fun validatePasswordsAndRegister(email: String, password: String) {
        var valid = true

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false
        }

        if (password != confirmPassword) {
            valid = false
        }

        _passwordsMatch.value = valid

        if (valid) {
            performRegistration(email, password)
        }
    }
}