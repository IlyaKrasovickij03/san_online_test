package com.san_online_test.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.san_online_test.data.location.DefaultLocationTracker
import com.san_online_test.data.network.api.WeatherAppApiNetwork
import com.san_online_test.data.repository.WeatherRepositoryImpl
import com.san_online_test.data.storage.LocalStorage
import com.san_online_test.domain.location.LocationTracker
import com.san_online_test.domain.repository.WeatherRepository
import com.san_online_test.navigation.TopDestinationsCollection
import com.san_online_test.weatherapp.presentation.favorites.navigation.FavoritesTopLevelDestination
import com.san_online_test.weatherapp.presentation.home.navigation.HomeTopLevelDestination
import com.san_online_test.weatherapp.presentation.profile.navigation.ProfileTopLevelDestination
import com.san_online_test.weatherapp.presentation.search.navigation.SearchTopLevelDestination
import com.san_online_test.weatherapp.presentation.settings.navigation.SettingsTopLevelDestination
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(
    private val application: Application,
    private val context: Context,
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "WeatherApp")

    @Provides
    fun provideWeatherRepository(
        weatherAppApiNetwork: WeatherAppApiNetwork
    ): WeatherRepository =
        WeatherRepositoryImpl(
            weatherAppApiNetwork = weatherAppApiNetwork
        )

    @Provides
    fun provideWeatherAppApiNetwork(
        moshi: Moshi,
        defaultLocationTracker: DefaultLocationTracker,
        context: Context,
        localStorage: LocalStorage
    ): WeatherAppApiNetwork {
        return WeatherAppApiNetwork(
            moshi = moshi,
            defaultLocationTracker = defaultLocationTracker,
            context = context,
            localStorage = localStorage
        )
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        application: Application,
        localStorage: LocalStorage
    ): LocationTracker {
        return DefaultLocationTracker(
            fusedLocationProviderClient = fusedLocationProviderClient,
            application = application,
            localStorage = localStorage
        )
    }

    @Provides
    fun provideFusedLocationProviderClient(
        application: Application
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideDefaultLocationTracker(
        fusedLocationProviderClient: FusedLocationProviderClient,
        application: Application,
        localStorage: LocalStorage
    ): DefaultLocationTracker {
        return DefaultLocationTracker(
            fusedLocationProviderClient = fusedLocationProviderClient,
            application = application,
            localStorage = localStorage
        )
    }

    @Provides
    fun provideLocalStorage(
        dataStore: DataStore<Preferences>
    ): LocalStorage {
        return LocalStorage(
            localDataBase = dataStore
        )
    }

    @Provides
    fun provideDataStore(
        context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideTopLevelDestinations(): TopDestinationsCollection {
        return TopDestinationsCollection(
            items = listOf(
                HomeTopLevelDestination(),
                SearchTopLevelDestination(),
                FavoritesTopLevelDestination(),
                ProfileTopLevelDestination(),
                SettingsTopLevelDestination()
            )
        )
    }


}
