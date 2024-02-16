package com.san_online_test.weatherapp.presentation.bottom_menu.navigation

import androidx.navigation.NavHostController
import com.san_online_test.weatherapp.presentation.home.navigation.HomeNavigator

fun NavHostController.homeNavigator(externalNavigator: BottomMenuNavigator): HomeNavigator =
    object : HomeNavigator {
        override fun navigateToDetails(weatherItemDate: String) {
            externalNavigator.onNavigateToDetails(weatherItemDate)
        }

        override fun onNavigateUp() {
            externalNavigator.onNavigateUp()
        }
    }