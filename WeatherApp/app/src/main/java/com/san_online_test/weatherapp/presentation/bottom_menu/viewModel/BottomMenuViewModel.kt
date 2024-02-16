package com.san_online_test.weatherapp.presentation.bottom_menu.viewModel

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.san_online_test.navigation.TopDestinationsCollection
import com.san_online_test.navigation.WeatherTopLevelDestination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class BottomMenuUiState(
    val topLevelDestinations: ImmutableList<WeatherTopLevelDestination>
)

class BottomMenuViewModel(
    private val topLevelDestinations: TopDestinationsCollection
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BottomMenuUiState(
            topLevelDestinations = topLevelDestinations.items.toPersistentList()
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    topLevelDestinations = currentState.topLevelDestinations
                        .toMutableList()
                        .toPersistentList()
                )
            }
        }
    }

    internal class Factory(
        private val topLevelDestinationsCollection: TopDestinationsCollection,
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            BottomMenuViewModel(
                topLevelDestinations = topLevelDestinationsCollection
            ) as T
    }
}



