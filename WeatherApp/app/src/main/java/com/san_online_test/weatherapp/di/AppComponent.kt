package com.san_online_test.weatherapp.di


import com.san_online_test.domain.usecases.GetCurrentUserLocationUseCase
import com.san_online_test.domain.usecases.GetFiveWeatherUseCase
import com.san_online_test.domain.usecases.GetOneAmongFiveWeatherUseCase
import com.san_online_test.navigation.TopDestinationsCollection
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    val getFiveWeatherUseCase: GetFiveWeatherUseCase
    val getOneAmongFiveWeatherUseCase: GetOneAmongFiveWeatherUseCase
    val getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase
    val topDestinationsCollection: TopDestinationsCollection
}