package com.san_online_test.weatherapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.BottomMenuDestination
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.bottomMenu
import com.san_online_test.weatherapp.presentation.home.navigation.HomeTopLevelDestination
import com.san_online_test.weatherapp.presentation.favorites.navigation.FavoritesTopLevelDestination
import com.san_online_test.weatherapp.presentation.profile.navigation.ProfileTopLevelDestination
import com.san_online_test.weatherapp.presentation.settings.navigation.SettingsTopLevelDestination
import com.san_online_test.weatherapp.presentation.search.navigation.SearchTopLevelDestination

@Composable
fun WeatherAppHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = BottomMenuDestination.route
    ){
        bottomMenu(
            topLevelDestination = listOf(
                HomeTopLevelDestination,
                SearchTopLevelDestination,
                FavoritesTopLevelDestination,
                ProfileTopLevelDestination,
                SettingsTopLevelDestination
            ),
        )
    }
}