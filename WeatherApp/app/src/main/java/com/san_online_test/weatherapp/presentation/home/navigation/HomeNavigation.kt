package com.san_online_test.weatherapp.presentation.home.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.san_online_test.navigation.WeatherTopLevelDestination
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.di.DiProvider
import com.san_online_test.weatherapp.presentation.home.screen.HomeScreen
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeViewModel


const val HOME_GRAPH = "home_graph"

data object HomeDestination : WeatherAppDestination {
    override val route = "$HOME_GRAPH/home"
}
interface HomeNavigator{
    fun navigateToDetails(id:String)
    fun onNavigateUp()
}

fun NavGraphBuilder.home(externalNavigator: HomeNavigator) {
    navigation(startDestination = HomeDestination.route, route = HOME_GRAPH) {
        composable(route = HomeDestination.route) {
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.Factory(
                    getAllWeatherUseCase = DiProvider.appComponent.getAllWeatherUseCase,
                    getFiveWeatherUseCase = DiProvider.appComponent.getFiveWeatherUseCase,
                    getCurrentUserLocationUseCase = DiProvider.appComponent.getCurrentUserLocationUseCase
                )
            )
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                uiState = uiState,
                onItemSelected = externalNavigator::navigateToDetails,
            )
        }
    }
}


data class HomeTopLevelDestination(
    override val iconId: Int = R.drawable.ic_home,
    override val titleId: Int = R.string.home_screen_bottom,
    override val graph: String = HOME_GRAPH
) : WeatherTopLevelDestination

