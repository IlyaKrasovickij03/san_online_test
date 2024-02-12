package com.san_online_test.weatherapp.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.ui.theme.WeatherAppTheme
import com.san_online_test.ui.R
import com.san_online_test.ui.theme.padding16
import com.san_online_test.ui.theme.padding4
import com.san_online_test.ui.widgets.LoadingIndicator
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeUiState
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeViewModel
import com.san_online_test.weatherapp.presentation.home.widgets.WeatherCard
import kotlinx.collections.immutable.toImmutableList
import okhttp3.internal.immutableListOf


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState
) {
    val vm: HomeViewModel = viewModel()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = vm.isRefreshing,
        onRefresh = { vm.getSwipeList() }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = padding16)
                .padding(top = padding16)
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.home_screen),
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF23282B)
            )
            when (uiState) {
                is HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }

                is HomeUiState.Success -> {
                    HomeScreenContent(
                        uiState = uiState
                    )
                }

            }

        }
        PullRefreshIndicator(
            refreshing = vm.isRefreshing,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState.Success,
) {
    Text(
        modifier = Modifier
            .padding(top = padding4),
        text = uiState.currentCityName,
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFF23282B)
    )

    LazyColumn() {
        items(uiState.weatherForecast) {
            WeatherCard(
                modifier = Modifier,
                item = it,
                cardContainerColor = determineColorByTemperature(maxTemp = it.maxTemperature),
                onItemClicked = {}
            )
        }
    }
}

@Composable
private fun determineColorByTemperature(maxTemp: Double): Color {
    return if (maxTemp > 10.00) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.secondary
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    WeatherAppTheme {
        HomeScreen(
            uiState = HomeUiState.Success(
                currentCityName = "Saint-Peterburg",
                weatherForecast = listOf(
                    WeatherItem(
                        cityName = "Saint-Petersburg",
                        date = 12022024,
                        cloudyPercent = 30,
                        relativeHumidity = 100,
                        atmosphericPressure = 700,
                        windSpeed = 10.00,
                        minTemperature = 15.00,
                        maxTemperature = 20.00
                    ),
                    WeatherItem(
                        cityName = "Saint-Petersburg",
                        date = 12022024,
                        cloudyPercent = 30,
                        relativeHumidity = 100,
                        atmosphericPressure = 700,
                        windSpeed = 10.00,
                        minTemperature = 15.00,
                        maxTemperature = 20.00
                    ),
                    WeatherItem(
                        cityName = "Saint-Petersburg",
                        date = 12022024,
                        cloudyPercent = 30,
                        relativeHumidity = 100,
                        atmosphericPressure = 700,
                        windSpeed = 10.00,
                        minTemperature = 15.00,
                        maxTemperature = 20.00
                    )
                ).toImmutableList()
            )
        )
    }
}