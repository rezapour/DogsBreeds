package com.rezapour.dogsbreeds.features.breedslist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.components.ErrorComponent
import com.rezapour.dogsbreeds.base.components.Loading
import com.rezapour.dogsbreeds.domain.model.BreedDomain

@Composable
fun BreedsListScreen(viewModel: BreedsListViewModel, onNavigateToFavoriteScreen: () -> Unit) {

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
            retry = {},
            onFavoriteClicked = { breed ->
                if (breed.favorite) viewModel.deleteFavorite(breed) else viewModel.addFavorite(
                    breed
                )
            })
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    uiState: DataState<List<BreedDomain>>,
    retry: () -> Unit,
    onFavoriteClicked: (BreedDomain) -> Unit
) {
    when (uiState) {
        is DataState.Error -> ErrorComponent(
            modifier = modifier,
            messageId = uiState.messageId,
            retryClicked = retry
        )

        DataState.Loading -> Loading(modifier = modifier)
        is DataState.Success -> BreedsList(
            modifier = modifier,
            breeds = uiState.data,
            onFavoriteClicked = onFavoriteClicked
        )
    }
}
