package com.san_online_test.data.repository

import android.util.Log
import com.san_online_test.data.mappers.toDomain
import com.san_online_test.data.network.api.WeatherAppApiNetwork
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.repository.WeatherRepository
import kotlin.math.roundToInt

class WeatherRepositoryImpl(
    private val weatherAppApiNetwork: WeatherAppApiNetwork
): WeatherRepository {
    override suspend fun getAllWeather(): List<WeatherItem> {
        val result = weatherAppApiNetwork.getWeather().toDomain()
        Log.d("result", result.toString())
        return result

    }

    override suspend fun getWeatherByDate(date: String): List<WeatherItem> {
        val allWeatherList = weatherAppApiNetwork.getWeather().toDomain()
        return allWeatherList.filter { it.date == date }
    }

    override suspend fun getUniqueWeathers(): List<WeatherItem> {
        val allWeatherList = weatherAppApiNetwork.getWeather().toDomain()
        val fiveWeatherList = mutableListOf<WeatherItem>()

        val groupedByDate = allWeatherList.groupBy { it.date }

        for ((date, weatherItems) in groupedByDate) {
            val avgCloudyPercent = weatherItems.map { it.cloudyPercent }.average()
            val avgRelativeHumidity = weatherItems.map { it.relativeHumidity }.average()
            val avgAtmosphericPressure = weatherItems.map { it.atmosphericPressure }.average()
            val avgWindSpeed = weatherItems.map { it.windSpeed }.average().roundToInt()
            val avgMinTemp = weatherItems.minOfOrNull { it.minTemperature }?: 0.0
            val avgMaxTemp = weatherItems.maxOfOrNull { it.maxTemperature }?: 0.0
            val avgFeelsLike = weatherItems.map { it.feelsLike }.average().roundToInt()
            val avgVisibility = weatherItems.map { it.visibility }.average().roundToInt()
            val avgSnow = weatherItems.map { it.snow }.average().roundToInt()

            val avgWeatherItem = WeatherItem(
                cityName = weatherItems.first().cityName,
                date = date,
                cloudyPercent = avgCloudyPercent.toLong(),
                relativeHumidity = avgRelativeHumidity.toLong(),
                atmosphericPressure = avgAtmosphericPressure.toLong(),
                windSpeed = avgWindSpeed,
                minTemperature = avgMinTemp.toInt(),
                maxTemperature = avgMaxTemp.toInt(),
                feelsLike = avgFeelsLike,
                visibility = avgVisibility,
                snow = avgSnow
            )
            fiveWeatherList.add(avgWeatherItem)
        }
        return fiveWeatherList.take(5)
    }

}