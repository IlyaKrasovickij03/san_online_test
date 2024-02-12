package com.san_online_test.weatherapp.presentation.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.search.SearchScreen

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.search(){
    composable(SearchDestination.route){
        SearchScreen()
    }
}

object SearchDestination: WeatherAppDestination {
    override val route = SEARCH_ROUTE
}

object SearchTopLevelDestination: DestinationModel {
    override val iconId = R.drawable.ic_search
    override val titleId = R.string.search_screen_bottom
    override val route = SearchDestination.route
}