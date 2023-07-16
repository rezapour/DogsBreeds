package com.rezapour.dogsbreeds.features.breedslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rezapour.dogsbreeds.R
import com.rezapour.dogsbreeds.base.ui.Dimensions
import com.rezapour.dogsbreeds.base.ui.theme.DogsBreedsTheme
import com.rezapour.dogsbreeds.domain.model.BreedDomain
import com.rezapour.dogsbreeds.util.capitalize


@Composable
fun BreedItem(
    modifier: Modifier = Modifier,
    breedDomain: BreedDomain,
    checked: Boolean,
    onFavoriteClicked: (BreedDomain) -> Unit
) {
    Surface(
        modifier = modifier.shadow(Dimensions.shadow),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.paddingMedium),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BreedItemTitle(modifier = Modifier.weight(1f), title = breedDomain.title)
            BreedItemFavoriteIcon(checked = checked, onFavoriteClicked = {
                onFavoriteClicked(breedDomain)
            })

        }

    }
}

@Composable
private fun BreedItemTitle(modifier: Modifier, title: String) {
    Text(
        text = title.capitalize(),
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.tertiary,
        modifier = modifier
            .padding(start = Dimensions.paddingStart)
    )
}

@Composable
private fun BreedItemFavoriteIcon(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onFavoriteClicked: () -> Unit
) {
    IconButton(onClick = onFavoriteClicked, modifier = Modifier.padding(0.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
            contentDescription = null,
            modifier = modifier.size(Dimensions.favoriteIconSize),
            tint = if (checked) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
private fun BreedItemPreview() {
    DogsBreedsTheme() {
        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.paddingMedium)) {
            BreedItem(
                breedDomain = BreedDomain("shepherd australian", "australian","shepherd", false),
                checked = false,
                onFavoriteClicked = {})
            BreedItem(
                breedDomain = BreedDomain("shepherd australian", "australian","shepherd", false),
                checked = true,
                onFavoriteClicked = {})
        }
    }
}

@Preview
@Composable
private fun PreviewBreedItemFavoriteIcon() {
    DogsBreedsTheme {
        Column() {
            BreedItemFavoriteIcon(checked = true, onFavoriteClicked = {})
            BreedItemFavoriteIcon(checked = false, onFavoriteClicked = {})
        }
    }
}

