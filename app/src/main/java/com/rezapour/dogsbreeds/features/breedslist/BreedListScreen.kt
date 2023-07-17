package com.rezapour.dogsbreeds.features.breedslist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.rezapour.dogsbreeds.features.compos.Content

@Composable
fun BreedsListScreen(
    viewModel: BreedsListViewModel,
    onNavigateToFavoriteScreen: () -> Unit,
    onNavigateToBreedDetail: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                onFavoriteClicked = onNavigateToFavoriteScreen
            )
        }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            retry = {viewModel.loadData()},
            onFavoriteClicked = { breed ->
                if (breed.favorite) viewModel.deleteFavorite(breed) else viewModel.addFavorite(
                    breed
                )
            }, onItemClicked = { breedDomain ->
                viewModel.navigateToBreedDetail(breedDomain)
                onNavigateToBreedDetail()
            })
    }
}