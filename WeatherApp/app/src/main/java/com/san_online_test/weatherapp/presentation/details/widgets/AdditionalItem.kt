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
import androidx.compose.ui.unit.dp
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.ui.design.theme.padding16

@Composable
fun AdditionalItem(additionalInfo: Pair<Int, String>) {
    Card (modifier = Modifier
        .height(90.dp)
        .width(135.dp),
    ){
        Column (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text(modifier = Modifier,
                text = stringResource(id = additionalInfo.first),
                maxLines = 2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(modifier = Modifier,
                text = additionalInfo.second)
        }

    }
}

@Preview
@Composable
fun AdditionalItemPreview() {
    WeatherAppTheme {
        AdditionalItem(Pair(R.string.wind_speed, "10"))
    }
}