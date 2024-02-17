package com.san_online_test.weatherapp.presentation.details.viewModel

import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.san_online_test.domain.model.WeatherItem
import com.san_online_test.domain.usecases.GetOneAmongFiveWeatherUseCase
import com.san_online_test.domain.usecases.GetSingleWeatherUseCase
import com.san_online_test.weatherapp.presentation.details.navigation.DetailsDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Success(val weatherInThisDay: WeatherItem) : DetailsUiState
    data class Error(val message: String) : DetailsUiState
}

class DetailsViewModel(
    private val getOneAmongFiveWeatherUseCase: GetOneAmongFiveWeatherUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val weatherItemDate: String =
        savedStateHandle[DetailsDestination.argumentName]!!

    private val _uiState = MutableStateFlow<DetailsUiState>(
        DetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            Log.d("NETWORK", "ПОГОДА: "+weatherItemDate)
            val weatherInThisDay = getOneAmongFiveWeatherUseCase.execute(weatherItemDate)
            _uiState.update {
                DetailsUiState.Success(
                    weatherInThisDay = weatherInThisDay)
            }
        }
    }

    internal class Factory(
        private val getOneAmongFiveWeatherUseCase: GetOneAmongFiveWeatherUseCase,
    ) : AbstractSavedStateViewModelFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return DetailsViewModel(
                getOneAmongFiveWeatherUseCase = getOneAmongFiveWeatherUseCase,
                savedStateHandle = handle
            ) as T
        }
    }

}