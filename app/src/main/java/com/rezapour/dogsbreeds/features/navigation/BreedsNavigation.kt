package com.rezapour.dogsbreeds.features.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rezapour.dogsbreeds.features.breedslist.BreedsListScreen
import com.rezapour.dogsbreeds.features.favoritelist.FavoriteScreen


fun NavGraphBuilder.breedsNavigation(navController: NavController) {
    composable(Destinations.BreedsListScreen.route) {
        BreedsListScreen(
            hiltViewModel(),
            onNavigateToFavoriteScreen = { navController.navigate(route = Destinations.FavoriteListScreen.route) })
    }

    composable(Destinations.FavoriteListScreen.route) {
        FavoriteScreen(
            viewModel = hiltViewModel(),
            onBackPressed = { navController.popBackStack() }) {

        }
    }
}