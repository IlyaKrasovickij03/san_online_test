package com.san_online_test.ui.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme

@Composable
fun WeatherParamsTitle(
    paramTitleId: Int
) {
    Text(
        text = stringResource(id = paramTitleId),
        style = MaterialTheme.typography.titleSmall
    )
}

@Preview
@Composable
fun WeatherParamsTitlePreview() {
    WeatherAppTheme {
        WeatherParamsTitle(paramTitleId = R.string.cloudy_percent)
    }
}