package com.san_online_test.weatherapp.presentation.details.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.weatherapp.di.DiProvider
import com.san_online_test.weatherapp.presentation.details.screen.DetailsScreen
import com.san_online_test.weatherapp.presentation.details.viewModel.DetailsViewModel

fun NavHostController.navigateToDetails(menuItemId: String) {
    navigate("${DetailsDestination.route}/${menuItemId}")
}

data object DetailsDestination : WeatherAppDestination {
    const val argumentName = "menuItem"
    override val route = "details"
    val routeWithArgs = "${route}/{${argumentName}}"
    val arguments =
        listOf(navArgument(argumentName) { type = NavType.StringType })
}

fun NavGraphBuilder.details(onNavigateUp: () -> Unit) {
    composable(
        route = DetailsDestination.routeWithArgs,
        arguments = DetailsDestination.arguments
    ) {
        val viewModel: DetailsViewModel = viewModel(
            factory = DetailsViewModel.Factory(
                getOneWeatherUseCase = DiProvider.appComponent.getOneWeatherUseCase
            )
        )
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        DetailsScreen(
            uiState = uiState,
            onNavigateUp = onNavigateUp
        )
    }
}