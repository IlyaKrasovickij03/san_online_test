package com.san_online_test.weatherapp.presentation.home.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.home.screen.HomeScreen
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeViewModel


const val HOME_ROUTE = "home"

fun NavGraphBuilder.home(){
    composable(HomeDestination.route){
        val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory(
            getLastFiveWeatherUseCase =
        ))
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(uiState = )
    }
}

object HomeDestination: WeatherAppDestination {
    override val route = HOME_ROUTE
}

object HomeTopLevelDestination: DestinationModel {
    override val iconId = R.drawable.ic_home
    override val titleId = R.string.home_screen_bottom
    override val route = HomeDestination.route
}