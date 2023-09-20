package com.example.allknightrapp.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.allknightrapp.data.AuthState
import com.example.allknightrapp.retrofit.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(val authRepository: AuthRepository) {
    private val auth = FirebaseAuth.getInstance()

    private val _authState = mutableStateOf(AuthState())
    private val authState: State<AuthState> = _authState

    private val _accessTokenState = MutableStateFlow("")
    val accessTokenState = _accessTokenState.asStateFlow()

    fun performLogin(email: String, password: String) {
        _authState.value = authState.value.copy(loading = true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _authState.value = authState.value.copy(loading = false)
                if (task.isSuccessful) {
                    _authState.value = authState.value.copy(message = "Login successful")
                } else {
                    _authState.value = authState.value.copy(message = "Login failed")
                }
            }
    }

    suspend fun getAccessToken() {
        authRepository.getAccessToken()
            .catch {  }
            .collect {
                _accessTokenState.value = it.getOrDefault("")
            }
    }
}

data class AuthState(
    val loading: Boolean = false,
    val message: String? = null
)
