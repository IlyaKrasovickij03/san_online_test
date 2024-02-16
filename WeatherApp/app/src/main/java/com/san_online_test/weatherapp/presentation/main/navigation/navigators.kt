package com.san_online_test.weatherapp.presentation.main.navigation

import androidx.navigation.NavHostController
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.BottomMenuNavigator
import com.san_online_test.weatherapp.presentation.details.navigation.navigateToDetails

fun NavHostController.bottomNavigator(): BottomMenuNavigator =
    object : BottomMenuNavigator {
        override fun onNavigateToDetails(weatherItemDate: String) {
            navigateToDetails(weatherItemDate = weatherItemDate)
        }

        override fun onNavigateUp() {
            popBackStack()
        }
    }