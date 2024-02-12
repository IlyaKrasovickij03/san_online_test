package com.san_online_test.data.network.api

import com.san_online_test.data.network.model.WeatherNetwork

interface WeatherAppApi {
    suspend fun getWeather(): WeatherNetwork
}