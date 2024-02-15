package com.san_online_test.weatherapp.di

import com.san_online_test.domain.usecases.GetCurrentUserLocationUseCase
import com.san_online_test.domain.usecases.GetFiveWeatherUseCase
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    val getFiveWeatherUseCase: GetFiveWeatherUseCase
    val getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase
}