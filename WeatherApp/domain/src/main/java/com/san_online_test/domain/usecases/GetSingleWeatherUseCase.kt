@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.san_online_test.domain.usecases

import com.san_online_test.domain.UseCaseWithParams
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.repository.WeatherRepository
import javax.inject.Inject

class GetSingleWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
): UseCaseWithParams<List<WeatherItem>, String> {
    override suspend fun execute(weatherDate: String): List<WeatherItem> {
        return weatherRepository.getWeatherByDate(weatherDate)
    }
}