package com.san_online_test.weatherapp.presentation.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.space8

@Composable
fun HomeScreenFailed() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_failed),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(space8))
            SettingsButton()
        }
    }
}

@Preview
@Composable
fun HomeScreenFailedPreview() {
    WeatherAppTheme {
        HomeScreenFailed()
    }
}