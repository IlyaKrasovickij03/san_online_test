package com.san_online_test.weatherapp.presentation.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.ui.theme.WeatherAppTheme
import com.san_online_test.ui.theme.height160
import com.san_online_test.ui.theme.padding16

@Composable
fun WeatherCard(
    modifier: Modifier,
    item: WeatherItem,
    cardContainerColor: Color,
    onItemClicked:() -> Unit
) {
    Card(
        modifier = modifier
            .height(height160)
            .fillMaxWidth()
            .clickable { onItemClicked() },
        colors = CardDefaults.cardColors(containerColor = cardContainerColor),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(all = padding16)
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.date.toString())
                Row() {
                    Text(text = item.minTemperature.toString())
                    Text(text = item.maxTemperature.toString())
                }
                Row() {
                    Text(text = item.relativeHumidity.toString())
                    Text(text = item.atmosphericPressure.toString())
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = item.cloudyPercent.toString())
                Text(text = item.windSpeed.toString())
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
                date = 12022024,
                cloudyPercent = 30,
                relativeHumidity = 100,
                atmosphericPressure = 700,
                windSpeed = 10.00,
                minTemperature = 15.00,
                maxTemperature = 20.00
            )
        )
    }
}