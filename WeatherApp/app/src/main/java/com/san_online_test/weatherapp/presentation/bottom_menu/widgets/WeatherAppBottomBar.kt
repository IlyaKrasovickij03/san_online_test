package com.san_online_test.weatherapp.presentation.bottom_menu.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.ui.theme.WeatherAppTheme
import com.san_online_test.weatherapp.presentation.favorites.navigation.FavoritesTopLevelDestination
import com.san_online_test.weatherapp.presentation.home.navigation.HomeDestination
import com.san_online_test.weatherapp.presentation.home.navigation.HomeTopLevelDestination
import com.san_online_test.weatherapp.presentation.profile.navigation.ProfileTopLevelDestination
import com.san_online_test.weatherapp.presentation.search.navigation.SearchTopLevelDestination
import com.san_online_test.weatherapp.presentation.settings.navigation.SettingsTopLevelDestination


@Composable
fun WeatherAppBottomBar(
    currentDestination: String,
    onNavigateToTopLevel: (topRoute: String) -> Unit,
    destination: List<DestinationModel>,
    modifier: Modifier = Modifier,

) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.background
    ) {
        destination.forEach { item ->
            NavigationBarItem(
                selected = currentDestination == item.route,
                onClick = { onNavigateToTopLevel(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = ""
                    )
                },
                alwaysShowLabel = false,
                modifier = modifier,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}

@Preview
@Composable
fun WeatherAppBottomBarPreview() {
    WeatherAppTheme {
        WeatherAppBottomBar(
            currentDestination = HomeDestination.route,
            onNavigateToTopLevel = {},
            destination = listOf(
                HomeTopLevelDestination,
                SearchTopLevelDestination,
                FavoritesTopLevelDestination,
                ProfileTopLevelDestination,
                SettingsTopLevelDestination
            ),
            modifier = Modifier,
        )
    }
}

