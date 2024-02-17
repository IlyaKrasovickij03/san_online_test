package com.san_online_test.weatherapp.presentation.bottom_menu.screen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.san_online_test.navigation.navigateSingleTopTo
import com.san_online_test.ui.design.SystemBarsColorDisposableEffect
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.weatherapp.di.DiProvider
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.BottomMenuNavigator
import com.san_online_test.weatherapp.presentation.bottom_menu.navigation.homeNavigator
import com.san_online_test.weatherapp.presentation.bottom_menu.viewModel.BottomMenuViewModel
import com.san_online_test.weatherapp.presentation.bottom_menu.widgets.WeatherAppBottomBar
import com.san_online_test.weatherapp.presentation.favorites.navigation.favorites
import com.san_online_test.weatherapp.presentation.home.navigation.HOME_GRAPH
import com.san_online_test.weatherapp.presentation.home.navigation.home
import com.san_online_test.weatherapp.presentation.profile.navigation.profile
import com.san_online_test.weatherapp.presentation.search.navigation.search
import com.san_online_test.weatherapp.presentation.settings.navigation.settings

@Composable
fun BottomMenuScreen(externalNavigator: BottomMenuNavigator) {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.parent?.route
        ?: navBackStackEntry?.destination?.route

    val bottomMenuViewModel: BottomMenuViewModel = viewModel(
        factory = BottomMenuViewModel.Factory(
            topLevelDestinationsCollection = DiProvider.appComponent.topDestinationsCollection
        )
    )
    val bottomMenuUiState by bottomMenuViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            WeatherAppBottomBar(
                bottomUiState = bottomMenuUiState,
                currentDestination = currentRoute,
                onNavigateToTopLevel = { route ->
                    navController.navigateSingleTopTo(route)
                },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = HOME_GRAPH
            ) {
                home(navController.homeNavigator(externalNavigator))
                search()
                favorites()
                profile()
                settings()
            }
            SystemBarsColorDisposableEffect(true)
        }
    }
}

//@Preview
//@Composable
//fun BottomMenuScreenPreview() {
//    WeatherAppTheme {
//        BottomMenuScreen(
//            externalNavigator = object : BottomMenuNavigator {
//                override fun onNavigateToDetails(weatherItemDate: String) {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onNavigateUp() {
//                    TODO("Not yet implemented")
//                }
//            }
//
//        )
//    }
//}