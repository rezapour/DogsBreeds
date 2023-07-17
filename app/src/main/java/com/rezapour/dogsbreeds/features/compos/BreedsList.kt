package com.rezapour.dogsbreeds.features.compos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.rezapour.dogsbreeds.base.ui.Dimensions
import com.rezapour.dogsbreeds.base.ui.theme.DogsBreedsTheme
import com.rezapour.dogsbreeds.domain.model.BreedDomain

@Composable
fun BreedsList(
    modifier: Modifier = Modifier,
    breeds: List<BreedDomain>,
    onFavoriteClicked: (BreedDomain) -> Unit,
    onItemClicked:(BreedDomain)->Unit
) {
    LazyColumn(
        modifier = modifier.padding(Dimensions.paddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimensions.spaceBySmall),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = breeds) { breed ->
            BreedItem(
                breedDomain = breed,
                checked = breed.favorite,
                onFavoriteClicked = onFavoriteClicked,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Preview
@Composable
private fun BreedsListPreview() {
    DogsBreedsTheme() {
        BreedsList(
            breeds = listOf(
                BreedDomain(title = "basenji",name = "basenji", favorite = true),
                BreedDomain(title = "norwegian buhund",name = "buhund", type = "norwegian", favorite = false)
            ), onFavoriteClicked = {}, onItemClicked = {}
        )
    }
}