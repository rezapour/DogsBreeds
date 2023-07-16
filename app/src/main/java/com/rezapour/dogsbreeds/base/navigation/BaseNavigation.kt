package com.rezapour.dogsbreeds.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rezapour.dogsbreeds.features.navigation.Destinations
import com.rezapour.dogsbreeds.features.navigation.breedsNavigation

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Destinations.BreedsListScreen.route,
        modifier = modifier
    ) {
        breedsNavigation(navController = navController)
    }
}