package com.san_online_test.weatherapp.presentation.home.viewModel

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.delay
import androidx.lifecycle.viewModelScope
import com.san_online_test.data.network.api.WeatherAppApiNetwork
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.usecases.GetLastFiveWeatherUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    data object Loading : HomeUiState
    @Immutable
    data class Success(
        val weatherForecast: ImmutableList<WeatherItem>,
        val currentCityName: String,
    ): HomeUiState
}

class HomeViewModel(
    private val getLastFiveWeatherUseCase: GetLastFiveWeatherUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        initHome()
    }

    var isRefreshing by mutableStateOf(false)
    val api_key = "f6f4dc2da8092cb1c8b18c09dfd1e77f"
    val get = WeatherAppApiNetwork(
        apiUrl = "http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=$api_key",
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    fun getSwipeList() = viewModelScope.launch {
        isRefreshing = true
        delay(2000L)
        repeat(3) {
            Log.d("AAA", "Refresh")
            isRefreshing = false
        }
        delay(2000L)
        get.getWeather()
    }

    private fun initHome(){
        viewModelScope.launch {
            val weatherForecast = getLastFiveWeatherUseCase.execute()
            _uiState.update {
                HomeUiState.Success(
                    weatherForecast = weatherForecast.toImmutableList(),
                    currentCityName = weatherForecast[0].cityName
                )
            }
        }
    }

    internal class Factory(
        private val getLastFiveWeatherUseCase: GetLastFiveWeatherUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(
                getLastFiveWeatherUseCase = getLastFiveWeatherUseCase
            ) as T
    }
}