package com.san_online_test.data.mappers

import com.san_online_test.data.network.model.WeatherNetwork
import com.san_online_test.domain.model.WeatherItem

internal fun WeatherNetwork.toDomain(): List<WeatherItem>{
    val result = mutableListOf<WeatherItem>()
    list.forEach {
        result.add(WeatherItem(
            cityName = city.name,
            date = it.dt,
            cloudyPercent = it.clouds.all,
            relativeHumidity = it.main.humidity,
            atmosphericPressure = it.main.pressure,
            windSpeed = it.wind.speed,
            minTemperature = it.main.tempMin,
            maxTemperature = it.main.tempMax,
        ))
    }
    return result
}