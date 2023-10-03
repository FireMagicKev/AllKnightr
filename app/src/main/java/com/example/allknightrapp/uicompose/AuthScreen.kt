package com.example.allknightrapp.uicompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.allknightrapp.R
import com.example.allknightrapp.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AuthScreen(onLogin: (email: String, password: String) -> Unit) {
    val viewModel = hiltViewModel<AuthViewModel>()
    var authState = remember{viewModel.authState.value}
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.allknightr_logo), // Your image resource
            contentDescription = "Login Image",
            modifier = Modifier
                .size(300.dp) // Adjust size as needed
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Login",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp)) // Add more spacing

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Focus next input field or perform login action
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    onLogin(email, password)
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onLogin(email, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Log in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = authState.message ?: "")

        if (authState.loading) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview () {
    AuthScreen() { _, _ -> }
}