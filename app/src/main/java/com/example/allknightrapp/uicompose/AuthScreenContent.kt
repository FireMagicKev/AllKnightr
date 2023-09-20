package com.example.allknightrapp.uicompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.allknightrapp.auth.AuthViewModel


@Composable
fun AuthScreenContent(authViewModel: AuthViewModel = viewModel()) {
    val authState by authViewModel.authState.collectAsState()

    AuthScreen(authState) { email, password ->
        authViewModel.performLogin(email, password)
    }
}