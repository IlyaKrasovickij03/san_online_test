package com.san_online_test.ui.widgets

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.width64

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.width(width64),
        color = MaterialTheme.colorScheme.surfaceVariant,
        trackColor = MaterialTheme.colorScheme.secondary,
    )
}

@Preview()
@Composable
fun LoadingIndicatorPreview() {
    WeatherAppTheme {
        LoadingIndicator()
    }
}