package com.rezapour.dogsbreeds.features.navigation

sealed class Destinations(val route: String) {
    object BreedsListScreen : Destinations("breeds_list")

}