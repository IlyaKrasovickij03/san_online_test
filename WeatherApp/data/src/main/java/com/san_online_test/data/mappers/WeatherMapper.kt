package com.san_online_test.data.mappers

import com.san_online_test.data.network.model.WeatherNetwork
import com.san_online_test.domain.model.WeatherItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import kotlin.math.roundToInt

internal fun WeatherNetwork.toDomain(): List<WeatherItem>{
    val result = mutableListOf<WeatherItem>()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale("ru"))
    list.forEach {
        result.add(WeatherItem(
            cityName = city.name,
            date = LocalDateTime.parse(it.dtTxt, formatter).format(outputFormatter),
            cloudyPercent = it.clouds.all,
            relativeHumidity = it.main.humidity,
            atmosphericPressure = it.main.pressure,
            windSpeed = it.wind.speed.roundToInt(),
            minTemperature = it.main.tempMin.roundToInt(),
            maxTemperature = it.main.tempMax.roundToInt(),
            feelsLike = it.main.feelsLike.roundToInt(),
            visibility = it.visibility.toInt(),
            snow = it.snow?.n3h?.roundToInt() ?: 0
        ))
    }
    return result
}