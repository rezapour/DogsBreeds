package com.rezapour.dogsbreeds.features.navigation

sealed class Destinations(val route: String) {
    object BreedsListScreen : Destinations("breeds_list")
    object FavoriteListScreen : Destinations("favorite_list")
    object ImageListScreen : Destinations("image_list")

}