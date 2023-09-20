package com.example.allknightrapp.uicompose.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

object KeyboardUtils {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun hideKeyboard() {
        val keyboardController = LocalSoftwareKeyboardController.current
        keyboardController?.hide()
    }
}


