package com.san_online_test.weatherapp.presentation.bottom_menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.weatherapp.presentation.bottom_menu.screen.BottomMenuScreen

const val BOTTOM_MENU_ROUTE = "bottom_menu"

fun NavGraphBuilder.bottomMenu(
    topLevelDestination: List<DestinationModel>,
){
    composable(BottomMenuDestination.route){
        BottomMenuScreen(topLevelDestination = topLevelDestination)
    }
}

object BottomMenuDestination: WeatherAppDestination{
    override val route = BOTTOM_MENU_ROUTE
}

fun NavHostController.navigateToBottomMenu() {
    navigate(BottomMenuDestination.route)
}