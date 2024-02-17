package com.san_online_test.weatherapp.presentation.details.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.height4
import com.san_online_test.ui.design.theme.height90
import com.san_online_test.ui.design.theme.padding8
import com.san_online_test.ui.design.theme.width135

@Composable
fun AdditionalItem(additionalInfo: Triple<Int, String, String>) {
    Card (modifier = Modifier
        .height(height90)
        .width(width135),
    ){
        Column (
            modifier = Modifier
                .padding(padding8)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text(modifier = Modifier,
                text = stringResource(id = additionalInfo.first),
                maxLines = 2)
            Spacer(modifier = Modifier.height(height4))
            Text(modifier = Modifier,
                text = additionalInfo.second+additionalInfo.third)
        }

    }
}

@Preview
@Composable
fun AdditionalItemPreview() {
    WeatherAppTheme {
        AdditionalItem(Triple(R.string.wind_speed, "10", "м/c"))
    }
}