package com.san_online_test.weatherapp.presentation.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.san_online_test.navigation.WeatherTopLevelDestination
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.search.SearchScreen
import com.san_online_test.weatherapp.presentation.settings.SettingsScreen

const val SETTINGS_GRAPH = "SETTINGS_graph"

data object SettingsDestination : WeatherAppDestination {
    override val route = "$SETTINGS_GRAPH/search"
}

fun NavGraphBuilder.settings() {
    navigation(startDestination = SettingsDestination.route, route = SETTINGS_GRAPH) {
        composable(route = SettingsDestination.route) {
            SettingsScreen()
        }
    }
}

data class SettingsTopLevelDestination(
    override val iconId: Int = R.drawable.ic_settings,
    override val titleId: Int = R.string.settings_screen_bottom,
    override val graph: String = SETTINGS_GRAPH
) : WeatherTopLevelDestination