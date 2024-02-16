package com.san_online_test.domain.usecases

import com.san_online_test.domain.UseCaseWithoutParams
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.repository.WeatherRepository
import javax.inject.Inject
import kotlin.math.roundToInt

class GetFiveWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
): UseCaseWithoutParams<List<WeatherItem>> {
    override suspend fun execute(): List<WeatherItem> {
        val allWeatherList = weatherRepository.getAllWeather()
        val fiveWeatherList = mutableListOf<WeatherItem>()

        val groupedByDate = allWeatherList.groupBy { it.date }

        for ((date, weatherItems) in groupedByDate) {
            val avgCloudyPercent = weatherItems.map { it.cloudyPercent }.average()
            val avgRelativeHumidity = weatherItems.map { it.relativeHumidity }.average()
            val avgAtmosphericPressure = weatherItems.map { it.atmosphericPressure }.average()
            val avgWindSpeed = weatherItems.map { it.windSpeed }.average().roundToInt()
            val avgMinTemp = weatherItems.minOfOrNull { it.minTemperature }?: 0.0
            val avgMaxTemp = weatherItems.maxOfOrNull { it.maxTemperature }?: 0.0

            val avgWeatherItem = WeatherItem(
                cityName = weatherItems.first().cityName,
                date = date,
                cloudyPercent = avgCloudyPercent.toLong(),
                relativeHumidity = avgRelativeHumidity.toLong(),
                atmosphericPressure = avgAtmosphericPressure.toLong(),
                windSpeed = avgWindSpeed,
                minTemperature = avgMinTemp.toInt(),
                maxTemperature = avgMaxTemp.toInt()
            )
            fiveWeatherList.add(avgWeatherItem)
        }

        return fiveWeatherList.take(5)
    }
}