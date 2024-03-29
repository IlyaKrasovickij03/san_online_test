package com.san_online_test.weatherapp.presentation.home.viewModel

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.san_online_test.domain.model.Location
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.usecases.GetCurrentUserLocationUseCase
import com.san_online_test.domain.usecases.GetFiveWeatherUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
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
        val currentLocation: Location?
    ): HomeUiState
}

class HomeViewModel(
    private val getFiveWeatherUseCase: GetFiveWeatherUseCase,
    private val getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        initHome()
    }

    var isRefreshing by mutableStateOf(false)

    fun getSwipeList() = viewModelScope.launch {
        isRefreshing = true
        delay(2000L)
        isRefreshing = false
        delay(2000L)
        setCurrentLocation()
    }
    private fun initHome(){
        viewModelScope.launch {
            val fiveWeatherForecast = getFiveWeatherUseCase.execute()
            val location = getCurrentUserLocationUseCase.execute()
            _uiState.update {
                HomeUiState.Success(
                    currentLocation = location,
                    weatherForecast = fiveWeatherForecast.toImmutableList(),
                    currentCityName = fiveWeatherForecast[0].cityName
                )
            }

        }
    }

    fun getLocation(){
        viewModelScope.launch {
            val fiveWeatherForecast = getFiveWeatherUseCase.execute()
            val location = getCurrentUserLocationUseCase.execute()
            _uiState.value =
                HomeUiState.Success(
                    currentLocation = location,
                    currentCityName = fiveWeatherForecast[0].cityName,
                    weatherForecast = fiveWeatherForecast.toImmutableList()
                )


        }
    }

    private fun setCurrentLocation() {
        viewModelScope.launch {
            val fiveWeatherForecast = getFiveWeatherUseCase.execute()
            val location = getCurrentUserLocationUseCase.execute()
            _uiState.update {
                HomeUiState.Success(
                    currentLocation = location,
                    currentCityName = fiveWeatherForecast[0].cityName,
                    weatherForecast = fiveWeatherForecast.toImmutableList()
                )
            }

        }
    }
    internal class Factory(
        private val getFiveWeatherUseCase: GetFiveWeatherUseCase,
        private val getCurrentUserLocationUseCase: GetCurrentUserLocationUseCase,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(
                getCurrentUserLocationUseCase = getCurrentUserLocationUseCase,
                getFiveWeatherUseCase = getFiveWeatherUseCase
            ) as T
    }
}