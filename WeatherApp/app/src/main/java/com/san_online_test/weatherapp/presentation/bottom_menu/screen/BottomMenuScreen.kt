package com.san_online_test.weatherapp.presentation.bottom_menu.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.ui.theme.WeatherAppTheme
import com.san_online_test.weatherapp.presentation.bottom_menu.widgets.WeatherAppBottomBar
import com.san_online_test.weatherapp.presentation.home.navigation.HomeDestination
import com.san_online_test.weatherapp.presentation.home.navigation.HomeTopLevelDestination
import com.san_online_test.weatherapp.presentation.home.navigation.home
import com.san_online_test.weatherapp.presentation.favorites.navigation.favorites
import com.san_online_test.weatherapp.presentation.profile.navigation.profile
import com.san_online_test.weatherapp.presentation.settings.navigation.settings
import com.san_online_test.weatherapp.presentation.search.navigation.search

@Composable
fun BottomMenuScreen(
    topLevelDestination: List<DestinationModel>,
    navigateToProductDetailsScreen: () -> Unit = {}
) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != null) {
                WeatherAppBottomBar(
                    currentDestination = currentRoute,
                    onNavigateToTopLevel = { route ->
                        if (currentRoute != HomeTopLevelDestination.route)
                            navController.popBackStack()

                        if (route != HomeTopLevelDestination.route)
                            navController.navigate(route = route)
                    },
                    destination = topLevelDestination,
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = HomeDestination.route
            ) {
                home()
                search()
                favorites()
                profile()
                settings()
            }
        }
    }
}

@Preview
@Composable
fun BottomMenuScreenPreview() {
    WeatherAppTheme {
        BottomMenuScreen(
            topLevelDestination = listOf(),
            navigateToProductDetailsScreen = {}
        )
    }
}