package com.example.allknightrapp.uicompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.allknightrapp.auth.AuthViewModel

@Composable
fun RegistrationScreenContent(authViewModel: AuthViewModel = viewModel()) {
    val authState by authViewModel.authState.collectAsState()
    val passwordsMatch by authViewModel.passwordsMatch.collectAsState()

    RegistrationScreen(authState, passwordsMatch) { email, password ->
        authViewModel.validatePasswordsAndRegister(email, password)
    }
}