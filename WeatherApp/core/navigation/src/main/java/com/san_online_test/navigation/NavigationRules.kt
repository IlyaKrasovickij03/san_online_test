package com.san_online_test.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface WeatherAppDestination{
    val route: String
}

interface DestinationModel: WeatherAppDestination{
    @get:DrawableRes
    val iconId: Int
    @get:StringRes
    val titleId: Int
}