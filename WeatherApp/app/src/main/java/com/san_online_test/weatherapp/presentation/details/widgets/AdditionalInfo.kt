package com.san_online_test.weatherapp.presentation.details.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.space8
import com.san_online_test.weatherapp.presentation.details.viewModel.DetailsUiState

@Composable
fun AdditionalInfo(
    uiState: DetailsUiState.Success
) {
    val additionalInfo = mutableListOf<Triple<Int, String, String>>()
    additionalInfo.add(Triple(R.string.wind_speed, uiState.weatherInThisDay.windSpeed.toString(), "м/c"))
    additionalInfo.add(Triple(R.string.atmospheric_pressure, uiState.weatherInThisDay.atmosphericPressure.toString(), "кПа"))
    additionalInfo.add(Triple(R.string.relative_humidity, uiState.weatherInThisDay.relativeHumidity.toString(), "%"))
    additionalInfo.add(Triple(R.string.feels_like, uiState.weatherInThisDay.feelsLike.toString(), "°C"))
    additionalInfo.add(Triple(R.string.visibility, uiState.weatherInThisDay.visibility.toString(), "м"))
    additionalInfo.add(Triple(R.string.snow, uiState.weatherInThisDay.snow.toString(), ""))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space8)
    ) {
        items(additionalInfo) {
            AdditionalItem(additionalInfo = it)
        }
    }

}

@Preview
@Composable
fun AdditionalInfoPreview() {
    WeatherAppTheme {
        AdditionalInfo(uiState = DetailsUiState.Success(WeatherItem(
            cityName = "Saint-Petersburg",
            date = "16 февраля 2024г.",
            cloudyPercent = 30,
            relativeHumidity = 100,
            atmosphericPressure = 700,
            windSpeed = 10,
            minTemperature = 15,
            maxTemperature = 20,
            feelsLike = 1,
            visibility = 2,
            snow = 3
        )))
    }
}