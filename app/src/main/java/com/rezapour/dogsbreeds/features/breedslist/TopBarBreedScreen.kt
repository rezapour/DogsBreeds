package com.rezapour.dogsbreeds.features.breedslist

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rezapour.dogsbreeds.R
import com.rezapour.dogsbreeds.base.ui.Dimensions
import com.rezapour.dogsbreeds.base.ui.theme.DogsBreedsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    onFavoriteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.breeds_list_title),
                style = MaterialTheme.typography.titleMedium,
                fontSize = Dimensions.topBarFontSize,
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        actions = {
            TopBarActions(
                onFavoriteClicked = onFavoriteClicked,
                enableState = true,
                modifier = Modifier
            )
        })
}

@Composable
private fun TopBarActions(
    modifier: Modifier = Modifier,
    onFavoriteClicked: () -> Unit,
    enableState: Boolean
) {
    IconButton(
        onClick = onFavoriteClicked,
        enabled = enableState,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.baseline_favorite_border_24),
            contentDescription = null,
            modifier = Modifier.size(Dimensions.topBarIconSize),
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
private fun topBarPreview() {
    DogsBreedsTheme() {
        TopBar(onFavoriteClicked = {})
    }
}