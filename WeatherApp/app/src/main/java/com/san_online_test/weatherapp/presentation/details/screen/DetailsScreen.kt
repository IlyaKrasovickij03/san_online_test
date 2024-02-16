package com.san_online_test.weatherapp.presentation.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.padding16
import com.san_online_test.ui.widgets.LoadingIndicator
import com.san_online_test.ui.widgets.WeatherParamsTitle
import com.san_online_test.weatherapp.presentation.details.viewModel.DetailsUiState
import com.san_online_test.weatherapp.presentation.details.widgets.AdditionalItem

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    onNavigateUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = padding16)
                .padding(top = padding16)
        ) {
            IconButton(
                onClick = onNavigateUp
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.arrow_back_cd),
                    tint = Color.Unspecified
                )
            }
            when (uiState) {
                is DetailsUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }

                is DetailsUiState.Error -> {}
                is DetailsUiState.Success -> {
                    DetailsScreenContent(uiState = uiState)
                }
            }
        }
    }
}

@Composable
fun DetailsScreenContent(
    uiState: DetailsUiState.Success
) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Column(
                modifier = Modifier.padding(padding16)
            ) {
                Text(
                    text = uiState.weatherInThisDay.date,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = uiState.weatherInThisDay.cityName,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.min_temp)
                    Text(
                        text = uiState.weatherInThisDay.minTemperature.toString() + "°C",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.max_temp)
                    Text(
                        text = uiState.weatherInThisDay.maxTemperature.toString() + "°C",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.cloudy_percent)
                    Text(text = uiState.weatherInThisDay.cloudyPercent.toString() + "%")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        AdditionalInfo(uiState = uiState)
    }
}

@Composable
fun AdditionalInfo(
    uiState: DetailsUiState.Success
) {
    val additionalInfo = mutableListOf<Pair<Int, String>>()
    additionalInfo.add(Pair(R.string.wind_speed, uiState.weatherInThisDay.windSpeed.toString()))
    additionalInfo.add(Pair(R.string.atmospheric_pressure, uiState.weatherInThisDay.atmosphericPressure.toString()))
    additionalInfo.add(Pair(R.string.relative_humidity, uiState.weatherInThisDay.relativeHumidity.toString()))
    additionalInfo.add(Pair(R.string.feels_like, uiState.weatherInThisDay.feelsLike.toString()))
    additionalInfo.add(Pair(R.string.visibility, uiState.weatherInThisDay.visibility.toString()))
    additionalInfo.add(Pair(R.string.snow, uiState.weatherInThisDay.snow.toString()))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(additionalInfo) {
            AdditionalItem(additionalInfo = it)
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    WeatherAppTheme {
        DetailsScreen(
            uiState = DetailsUiState.Success(
                listOf(
                    WeatherItem(
                        cityName = "Санкт-Петербург",
                        date = "16 февраля 2024г.",
                        cloudyPercent = 50,
                        relativeHumidity = 20,
                        atmosphericPressure = 780,
                        windSpeed = 10,
                        minTemperature = -11,
                        maxTemperature = -8,
                        feelsLike = 1,
                        visibility = 2,
                        snow = 3
                    )
                ),
                weatherInThisDay = WeatherItem(
                    cityName = "Санкт-Петербург",
                    date = "16 февраля 2024г.",
                    cloudyPercent = 50,
                    relativeHumidity = 20,
                    atmosphericPressure = 780,
                    windSpeed = 10,
                    minTemperature = -11,
                    maxTemperature = -8,
                    feelsLike = 1,
                    visibility = 2,
                    snow = 3
                )
            ),
            onNavigateUp = {},
        )
    }
}