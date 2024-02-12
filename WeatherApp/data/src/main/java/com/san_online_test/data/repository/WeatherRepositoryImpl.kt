package com.san_online_test.data.repository

import com.san_online_test.data.mappers.toDomain
import com.san_online_test.data.network.api.WeatherAppApiNetwork
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherAppApiNetwork: WeatherAppApiNetwork
): WeatherRepository {
    override suspend fun getAllWeather(): List<WeatherItem> {
        return weatherAppApiNetwork.getWeather().toDomain()
    }

}