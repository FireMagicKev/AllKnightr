package com.example.allknightrapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allknightrapp.models.GenresResponseItem
import com.example.allknightrapp.retrofit.GenreRepository
import com.example.allknightrapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(val repository: GenreRepository) : ViewModel() {
    private val _genreState = MutableStateFlow<State<List<GenresResponseItem>>>(State.Loading)
    val genreState = _genreState.asStateFlow()

    fun getGenres() {
        viewModelScope.launch {
            repository
                .getGenre()
                .collect {
                    _genreState.value = it
                }
        }
    }
}