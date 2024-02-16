package com.san_online_test.weatherapp.di

import com.san_online_test.domain.usecases.GetCurrentUserLocationUseCase
import com.san_online_test.domain.usecases.GetAllWeatherUseCase
import com.san_online_test.domain.usecases.GetFiveWeatherUseCase
import com.san_online_test.domain.usecases.GetOneWeatherUseCase
import com.san_online_test.navigation.TopDestinationsCollection
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    val getAllWeatherUseCase: GetAllWeatherUseCase
    val getFiveWeatherUseCase: GetFiveWeatherUseCase
    val getOneWeatherUseCase: GetOneWeatherUseCase
    val getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase
    val topDestinationsCollection: TopDestinationsCollection
}