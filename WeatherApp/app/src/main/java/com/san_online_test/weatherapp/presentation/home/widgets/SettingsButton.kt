package com.san_online_test.weatherapp.presentation.home.widgets

import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme

@Composable
fun SettingsButton() {
    val context = LocalContext.current
    Button(onClick = {
        val intent = Intent(Settings.ACTION_SETTINGS)
        context.startActivity(intent)
    }) {
        Text(
            text = stringResource(id = R.string.open_settings),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
fun SettingsButtonPreview() {
    WeatherAppTheme {
        SettingsButton()
    }
}