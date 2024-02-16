package com.san_online_test.weatherapp.presentation.home.screen

import android.Manifest
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.san_online_test.ui.design.theme.padding16
import com.san_online_test.ui.design.theme.padding4
import com.san_online_test.ui.widgets.LoadingIndicator
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeUiState
import com.san_online_test.weatherapp.presentation.home.viewModel.HomeViewModel
import com.san_online_test.weatherapp.presentation.home.widgets.WeatherCard


@OptIn(ExperimentalMaterialApi::class)
@Composable

fun HomeScreen(
    uiState: HomeUiState,
    onItemSelected: (itemDate: String) -> Unit
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
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = padding16)
                .padding(top = padding16)
        ) {

            when (uiState) {
                is HomeUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
//                        LocationPermissionRequest()
                        LoadingIndicator()
                    }
                }

                is HomeUiState.Success -> {
                    HomeScreenContent(
                        uiState = uiState,
                        onItemSelected = onItemSelected
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
    onItemSelected: (String) -> Unit
) {
    Text(
        modifier = Modifier,
        text = stringResource(id = R.string.weather),
        style = MaterialTheme.typography.titleLarge,
        color = Color(0xFF23282B)
    )
    Text(
        modifier = Modifier
            .padding(top = padding4),
        text = uiState.currentCityName,
        style = MaterialTheme.typography.titleMedium,
        color = Color(0xFF23282B)
    )
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.weatherForecast) {
            WeatherCard(
                modifier = Modifier,
                item = it,
                cardContainerColor = colorDefByTemp(
                    minTemp = it.minTemperature,
                    maxTemp = it.maxTemperature,
                    countOfMeasure = 4
                ),
                onItemClicked = onItemSelected
            )
        }
    }
}

fun colorDefByTemp(minTemp: Int, maxTemp: Int, countOfMeasure: Int): Color{
    val averageTemp = (minTemp + maxTemp) / countOfMeasure
    return if (averageTemp <= 0) Color(0xFF4285B4)
    else if (averageTemp in 1..9) Color(0xFFFBEC5D)
    else if (averageTemp > 10) Color(0xFFFF8800)
    else Color(0xFFFFFFFF)
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionRequest() {
    var result by remember { mutableStateOf(true) }
    if (result){
        val permissionState = rememberMultiplePermissionsState(
            listOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()){}

        LaunchedEffect(permissionState) {
            permissionState.launchMultiplePermissionRequest()
        }

        when {
            permissionState.allPermissionsGranted  -> {
                result = false
                Log.d("CLOSE", result.toString())
            }
            permissionState.shouldShowRationale -> {

                AlertDialog(
                    onDismissRequest = { /*TODO*/ },
                    title = { Text("Необходимо разрешение на геолокацию") },
                    text = { Text("Это приложение требует разрешение на геолокацию для работы некоторых функций.") },
                    confirmButton = {
                        Button(onClick = { launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                            Text("Разрешить")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { /*TODO*/ }) {
                            Text("Отмена")
                        }
                    }
                )
            }
            else -> {
                // Запрос разрешения
                Button(onClick = { launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                    Text("Запросить разрешение на геолокацию")
                }
            }
        }
    }

}
