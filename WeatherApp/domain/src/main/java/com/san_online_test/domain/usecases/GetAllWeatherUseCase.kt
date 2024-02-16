package com.san_online_test.domain.usecases

import com.san_online_test.domain.UseCaseWithoutParams
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.repository.WeatherRepository
import javax.inject.Inject

class GetAllWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : UseCaseWithoutParams<List<WeatherItem>> {
    override suspend fun execute(): List<WeatherItem> {
        return weatherRepository.getAllWeather()
    }
}
