package com.san_online_test.weatherapp.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.settings_screen))
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    WeatherAppTheme {
        SettingsScreen()
    }
}