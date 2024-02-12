package com.san_online_test.weatherapp.presentation.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.favorites.FavoritesScreen

const val FAVORITES_ROUTE = "favorites"

fun NavGraphBuilder.favorites(){
    composable(FavoritesDestination.route){
        FavoritesScreen()
    }
}

object FavoritesDestination: WeatherAppDestination {
    override val route = FAVORITES_ROUTE
}

object FavoritesTopLevelDestination: DestinationModel {
    override val iconId = R.drawable.ic_favorites
    override val titleId = R.string.favorites_screen_bottom
    override val route = FavoritesDestination.route
}