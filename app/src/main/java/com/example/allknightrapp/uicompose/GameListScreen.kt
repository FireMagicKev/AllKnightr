package com.example.allknightrapp.uicompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.allknightrapp.models.GenresResponseItem
import com.example.allknightrapp.util.State
import com.example.allknightrapp.viewmodels.GameListViewModel

@Composable
fun GameListScreen() {
    val viewModel = hiltViewModel<GameListViewModel>()
    val genresState = remember { viewModel.genreState.value }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getGenres()
    })

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        when (genresState) {
            is State.Error -> {}
            State.Loading -> {}
            is State.Success -> {
                val genres = genresState.value
                items(genres) { genre ->
                Box(Modifier.aspectRatio(1f)) {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(genre.url)
                                .build(),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            contentDescription = "",
                        )
                    }

                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = genre.name,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }}
        }

    }
}

@Preview
@Composable
fun GameScreenListPreview() {
    val sampleGenres = listOf("Action", "Adventure", "First Person Shooter").map { GenresResponseItem(name = it) }
    GameListScreen()
}
