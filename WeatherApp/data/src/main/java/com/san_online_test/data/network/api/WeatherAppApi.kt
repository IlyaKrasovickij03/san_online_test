package com.san_online_test.data.network.api

import com.san_online_test.data.location.DefaultLocationTracker
import com.san_online_test.data.network.model.WeatherNetwork

interface WeatherAppApi {
    suspend fun createApiWithLocation(defaultLocationTracker: DefaultLocationTracker): String
    suspend fun getWeather(): WeatherNetwork
}