package com.san_online_test.weatherapp.presentation.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.san_online_test.navigation.WeatherTopLevelDestination
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.favorites.FavoritesScreen
import com.san_online_test.weatherapp.presentation.profile.ProfileScreen

const val FAVORITES_GRAPH = "favorites_graph"

data object FavoritesDestination : WeatherAppDestination {
    override val route = "$FAVORITES_GRAPH/favorites"
}

data class FavoritesTopLevelDestination(
    override val iconId: Int = R.drawable.ic_favorites,
    override val titleId: Int = R.string.favorites_screen_bottom,
    override val graph: String = FAVORITES_GRAPH
) : WeatherTopLevelDestination


fun NavGraphBuilder.favorites() {
    navigation(startDestination = FavoritesDestination.route, route = FAVORITES_GRAPH){
        composable(route = FavoritesDestination.route) {
            FavoritesScreen()
        }
    }
}