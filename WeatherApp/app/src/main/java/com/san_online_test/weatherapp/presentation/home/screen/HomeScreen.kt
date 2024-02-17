package com.san_online_test.weatherapp.presentation.home.screen

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.height16
import com.san_online_test.ui.design.theme.padding16
import com.san_online_test.ui.design.theme.padding4
import com.san_online_test.ui.design.theme.space8
import com.san_online_test.ui.widgets.LoadingIndicator
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeUiState
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeViewModel
import com.san_online_test.weatherapp.presentation.home.widgets.HomeScreenFailed
import com.san_online_test.weatherapp.presentation.home.widgets.LocationAlertDialog
import com.san_online_test.weatherapp.presentation.home.widgets.WeatherCard
import kotlinx.collections.immutable.toImmutableList


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun HomeScreen(
    uiState: HomeUiState, onItemSelected: (itemDate: String) -> Unit,
) {
    val homeViewModel: HomeViewModel = viewModel()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = homeViewModel.isRefreshing,
        onRefresh = { homeViewModel.getSwipeList() })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pullRefresh(pullRefreshState)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = padding16)
                .padding(top = padding16)
        ) {
            LocationPermissionRequest(
                uiState = uiState,
                homeViewModel = homeViewModel,
                onItemSelected = onItemSelected
            )
        }
        PullRefreshIndicator(
            refreshing = homeViewModel.isRefreshing,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState.Success, onItemSelected: (String) -> Unit
) {
    Text(
        modifier = Modifier,
        text = stringResource(id = R.string.weather),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        modifier = Modifier.padding(top = padding4),
        text = uiState.currentCityName,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(modifier = Modifier.height(height16))
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space8)
    ) {
        items(uiState.weatherForecast) {
            WeatherCard(
                modifier = Modifier,
                item = it,
                cardContainerColor = colorDefByTemp(
                    minTemp = it.minTemperature, maxTemp = it.maxTemperature, countOfMeasure = 4
                ),
                onItemClicked = onItemSelected
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionRequest(
    uiState: HomeUiState,
    homeViewModel: HomeViewModel,
    onItemSelected: (itemDate: String) -> Unit
) {
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {}

    LaunchedEffect(permissionState) {
        permissionState.launchMultiplePermissionRequest()
    }

    when {
        permissionState.allPermissionsGranted -> {
            homeViewModel.getLocation()
            when (uiState) {
                is HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }
                is HomeUiState.Success -> {
                    HomeScreenContent(
                        uiState = uiState, onItemSelected = onItemSelected
                    )
                }
            }
        }
        permissionState.shouldShowRationale -> {
            LocationAlertDialog(launcher = launcher)
        }
        else -> {
            HomeScreenFailed()
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() {
    WeatherAppTheme {
        HomeScreen(uiState = HomeUiState.Success(
            weatherForecast = listOf<WeatherItem>().toImmutableList(),
            currentCityName = "Санкт-Петербург",
            currentLocation = null
        ), onItemSelected = {})
    }
}

fun colorDefByTemp(minTemp: Int, maxTemp: Int, countOfMeasure: Int): Color {
    val averageTemp = (minTemp + maxTemp) / countOfMeasure
    return if (averageTemp <= 0) Color(0xFF4285B4)
    else if (averageTemp in 1..9) Color(0xFFFBEC5D)
    else if (averageTemp > 10) Color(0xFFFF8800)
    else Color(0xFFFFFFFF)
}
