package com.example.allknightrapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.allknightrapp.uicompose.AuthScreen
import com.example.allknightrapp.uicompose.GameListScreen
import com.example.allknightrapp.uicompose.RegistrationScreen

@Composable
fun AllKnightrApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gameListScreen") {
        composable("authScreen") { AuthScreen({ _, _ -> }) }
        composable("registrationScreen") { RegistrationScreen({ _, _ -> }) }
        composable("gameListScreen") { GameListScreen() }
        /*...*/
    }
}