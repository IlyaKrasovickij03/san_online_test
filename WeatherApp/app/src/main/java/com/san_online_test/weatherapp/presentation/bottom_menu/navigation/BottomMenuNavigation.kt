package com.san_online_test.weatherapp.presentation.bottom_menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.weatherapp.presentation.bottom_menu.screen.BottomMenuScreen
import com.san_online_test.weatherapp.presentation.details.navigation.details

const val BOTTOM_MENU_ROUTE = "bottom_menu"

interface BottomMenuNavigator {
    fun onNavigateToDetails(weatherItemDate: String)
    fun onNavigateUp()
}

fun NavGraphBuilder.bottomMenu(externalNavigator: BottomMenuNavigator){
    composable(BottomMenuDestination.route){
        BottomMenuScreen(externalNavigator)
    }
    details(onNavigateUp = externalNavigator::onNavigateUp)
}

object BottomMenuDestination: WeatherAppDestination{
    override val route = BOTTOM_MENU_ROUTE
}

fun NavHostController.navigateToBottomMenu() {
    navigate(BottomMenuDestination.route)
}