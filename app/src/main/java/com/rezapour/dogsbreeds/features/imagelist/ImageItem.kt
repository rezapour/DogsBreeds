package com.rezapour.dogsbreeds.features.imagelist

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.rezapour.dogsbreeds.base.components.ImageLoader
import com.rezapour.dogsbreeds.base.ui.Dimensions


@Composable
fun ImageItem(modifier: Modifier = Modifier, url: String) {
    ImageLoader(modifier=modifier.height(Dimensions.imageSize).shadow(Dimensions.shadow),imageUrl = url)
}