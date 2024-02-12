package com.san_online_test.domain.model

data class WeatherItem(
    val cityName: String,
    val date: Long,
    val cloudyPercent: Long,
    val relativeHumidity: Long,
    val atmosphericPressure: Long,
    val windSpeed: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
)
