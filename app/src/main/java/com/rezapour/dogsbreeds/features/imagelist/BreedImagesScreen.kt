package com.rezapour.dogsbreeds.features.imagelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.components.ErrorComponent
import com.rezapour.dogsbreeds.base.components.Loading
import com.rezapour.dogsbreeds.base.ui.Dimensions
import com.rezapour.dogsbreeds.domain.model.BreedDomain

@Composable
fun ImageListScreen(
    viewModel: BreedDetailViewModel,
    onBackClicked: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                onBackClicked = onBackClicked
            )
        }
    ) { paddingValues ->
        Content(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            retry = {},
            onImageClicked = {})
    }

}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    uiState: DataState<List<String>>,
    retry: () -> Unit,
    onImageClicked: (BreedDomain) -> Unit
) {
    when (uiState) {
        is DataState.Error -> ErrorComponent(
            modifier = modifier,
            messageId = uiState.messageId,
            retryClicked = retry
        )

        DataState.Loading -> Loading(modifier = modifier)
        is DataState.Success -> ImageList(modifier = modifier, images = uiState.data)
    }
}


@Composable
fun ImageList(modifier: Modifier = Modifier, images: List<String>) {
    LazyVerticalGrid(
        modifier = modifier.padding(start = Dimensions.paddingStart, end = Dimensions.paddingStart),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.spaceBySmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spaceBySmall)
    ) {
        items(images) { url ->
            ImageItem(url = url)
        }
    }
}

