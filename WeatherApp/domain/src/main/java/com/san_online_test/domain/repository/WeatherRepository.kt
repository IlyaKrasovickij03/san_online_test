package com.san_online_test.domain.repository

import com.san_online_test.domain.model.WeatherItem

interface WeatherRepository {
    suspend fun getAllWeather(): List<WeatherItem>
    suspend fun getWeatherByDate(date: String): List<WeatherItem>
    suspend fun getUniqueWeathers(): List<WeatherItem>
}