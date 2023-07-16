package com.rezapour.dogsbreeds.features.imagelist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rezapour.dogsbreeds.R
import com.rezapour.dogsbreeds.base.ui.Dimensions
import com.rezapour.dogsbreeds.base.ui.theme.DogsBreedsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.image_list_title),
                style = MaterialTheme.typography.titleMedium,
                fontSize = Dimensions.topBarFontSize,
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        })
}

@Preview
@Composable
private fun topBarPreview() {
    DogsBreedsTheme() {
        TopBar(onBackClicked = {})
    }
}