@file:Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package com.san_online_test.domain.usecases

import com.san_online_test.domain.UseCaseWithParams
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.repository.WeatherRepository
import javax.inject.Inject

class GetOneAmongFiveWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
): UseCaseWithParams<WeatherItem, String> {
    override suspend fun execute(weatherDate: String): WeatherItem {
       return weatherRepository.getUniqueWeathers().filter { it.date == weatherDate }[0]
    }
}