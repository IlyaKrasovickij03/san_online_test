package com.san_online_test.weatherapp.presentation.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.height160
import com.san_online_test.ui.design.theme.padding16
import com.san_online_test.ui.R
import com.san_online_test.ui.widgets.WeatherParamsTitle

@Composable
fun WeatherCard(
    modifier: Modifier,
    item: WeatherItem,
    cardContainerColor: Color,
    onItemClicked: (weatherDate: String) -> Unit
) {
    Card(
        modifier = modifier
            .height(height160)
            .fillMaxWidth()
            .clickable { onItemClicked(item.date) },
        colors = CardDefaults.cardColors(containerColor = cardContainerColor),
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = padding16),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                    .fillMaxHeight(),
            ) {
                Text(text = item.date,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(25.dp))
                Row (horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.min_temp)
                    Text(text = item.minTemperature.toString() + "°C",
                        style = MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row (horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.max_temp)
                    Text(text = item.maxTemperature.toString() + "°C",
                        style = MaterialTheme.typography.titleLarge)
                }
                Spacer(modifier = Modifier.height(1.dp))
            }

            Column(modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row (verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.cloudy_percent)
                    Text(text = item.cloudyPercent.toString() + "%")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row (verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.wind_speed)
                    Text(text = item.windSpeed.toString() + "м/с")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row (verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.relative_humidity)
                    Text(text = item.relativeHumidity.toString()+"%")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row (verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherParamsTitle(R.string.atmospheric_pressure)
                    Text(text = item.atmosphericPressure.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherCardPreview() {
    WeatherAppTheme {
        WeatherCard(
            cardContainerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier,
            onItemClicked = {},
            item = WeatherItem(
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
            )
        )
    }
}