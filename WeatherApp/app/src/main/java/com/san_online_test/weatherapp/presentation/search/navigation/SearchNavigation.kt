package com.san_online_test.weatherapp.presentation.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.san_online_test.navigation.WeatherTopLevelDestination
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.search.SearchScreen

const val SEARCH_GRAPH = "search_graph"

data object SearchDestination : WeatherAppDestination {
    override val route = "$SEARCH_GRAPH/search"
}

fun NavGraphBuilder.search() {
    navigation(startDestination = SearchDestination.route, route = SEARCH_GRAPH) {
        composable(route = SearchDestination.route) {
            SearchScreen()
        }
    }
}

data class SearchTopLevelDestination(
    override val iconId: Int = R.drawable.ic_search,
    override val titleId: Int = R.string.search_screen_bottom,
    override val graph: String = SEARCH_GRAPH
) : WeatherTopLevelDestination
