package com.rezapour.dogsbreeds.features.compos

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rezapour.dogsbreeds.DataState
import com.rezapour.dogsbreeds.base.components.ErrorComponent
import com.rezapour.dogsbreeds.base.components.Loading
import com.rezapour.dogsbreeds.domain.model.BreedDomain

@Composable
fun Content(
    modifier: Modifier = Modifier,
    uiState: DataState<List<BreedDomain>>,
    retry: () -> Unit,
    onFavoriteClicked: (BreedDomain) -> Unit,
    onItemClicked: (BreedDomain) -> Unit
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
            onFavoriteClicked = onFavoriteClicked,
            onItemClicked = onItemClicked
        )
    }
}
