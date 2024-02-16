package com.san_online_test.weatherapp.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.BottomMenuDestination
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.bottomMenu
import com.san_online_test.weatherapp.presentation.main.navigation.bottomNavigator

@Composable
fun WeatherAppHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = BottomMenuDestination.route
    ){
        bottomMenu(navController.bottomNavigator())
    }
}