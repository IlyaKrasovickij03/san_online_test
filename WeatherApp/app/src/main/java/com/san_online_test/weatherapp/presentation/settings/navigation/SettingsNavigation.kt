package com.san_online_test.weatherapp.presentation.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.settings.SettingsScreen

const val SETTINGS_ROUTE = "settings"

fun NavGraphBuilder.settings(){
    composable(SettingsDestination.route){
        SettingsScreen()
    }
}

object SettingsDestination: WeatherAppDestination {
    override val route = SETTINGS_ROUTE
}

object SettingsTopLevelDestination: DestinationModel {
    override val iconId = R.drawable.ic_settings
    override val titleId = R.string.settings_screen_bottom
    override val route = SettingsDestination.route
}