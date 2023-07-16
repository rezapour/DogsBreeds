package com.rezapour.dogsbreeds.features.favoritelist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.rezapour.dogsbreeds.features.breedslist.Content

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    onBackPressed: () -> Unit,
    onItemPressed: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                onBackClicked = onBackPressed
            )
        }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            retry = {},
            onFavoriteClicked = { breed ->
                if (breed.favorite) viewModel.deleteFavorite(breed) else viewModel.addFavorite(
                    breed
                )
            })
    }
}