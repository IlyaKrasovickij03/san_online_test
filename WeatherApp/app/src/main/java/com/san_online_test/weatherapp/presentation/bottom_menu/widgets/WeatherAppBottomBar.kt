package com.san_online_test.weatherapp.presentation.bottom_menu.widgets

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.design.theme.WeatherAppTheme
import com.san_online_test.weatherapp.presentation.bottom_menu.viewModel.BottomMenuUiState
import com.san_online_test.weatherapp.presentation.favorites.navigation.FavoritesTopLevelDestination
import com.san_online_test.weatherapp.presentation.home.navigation.HomeTopLevelDestination
import com.san_online_test.weatherapp.presentation.profile.navigation.ProfileTopLevelDestination
import com.san_online_test.weatherapp.presentation.search.navigation.SEARCH_GRAPH
import com.san_online_test.weatherapp.presentation.search.navigation.SearchTopLevelDestination
import com.san_online_test.weatherapp.presentation.settings.navigation.SettingsTopLevelDestination
import kotlinx.collections.immutable.persistentListOf


@Composable
fun WeatherAppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: String?,
    onNavigateToTopLevel: (topRoute: String) -> Unit,
    bottomUiState: BottomMenuUiState


    ) {
    WeatherAppNavBar {
        bottomUiState.topLevelDestinations.forEachIndexed { index, item ->
            AnimatedIconTab(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                iconResourceId = item.iconId,
                contentDescription = stringResource(item.titleId),
                selected = currentDestination == item.graph,
                onClicked = { onNavigateToTopLevel(item.graph) },
                selectedColor = MaterialTheme.colorScheme.primary,
                notSelectedColor = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
fun WeatherAppNavBarPreview() {
    WeatherAppTheme {
        WeatherAppBottomBar(
            bottomUiState = BottomMenuUiState(
                topLevelDestinations = persistentListOf(
                    HomeTopLevelDestination(),
                    SearchTopLevelDestination(),
                    FavoritesTopLevelDestination(),
                    ProfileTopLevelDestination(),
                    SettingsTopLevelDestination()
                )
            ),
            currentDestination = SEARCH_GRAPH,
            onNavigateToTopLevel = {},
        )
    }
}

