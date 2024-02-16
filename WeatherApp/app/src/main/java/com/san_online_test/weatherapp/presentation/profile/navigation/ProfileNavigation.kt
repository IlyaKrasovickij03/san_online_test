package com.san_online_test.weatherapp.presentation.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.san_online_test.navigation.WeatherTopLevelDestination
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.profile.ProfileScreen

const val PROFILE_GRAPH = "profile_graph"

data object ProfileDestination : WeatherAppDestination {
    override val route = "$PROFILE_GRAPH/profile"
}

data class ProfileTopLevelDestination(
    override val iconId: Int = R.drawable.ic_person,
    override val titleId: Int = R.string.profile_screen_bottom,
    override val graph: String = PROFILE_GRAPH
) : WeatherTopLevelDestination


fun NavGraphBuilder.profile() {
    navigation(startDestination = ProfileDestination.route, route = PROFILE_GRAPH){
        composable(route = ProfileDestination.route) {
            ProfileScreen()
        }
    }
}