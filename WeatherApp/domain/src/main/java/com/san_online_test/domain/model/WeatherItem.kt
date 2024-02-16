package com.san_online_test.domain.model


data class WeatherItem(
    val cityName: String,
    val date: String,
    val cloudyPercent: Long,
    val relativeHumidity: Long,
    val atmosphericPressure: Long,
    val windSpeed: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
)
