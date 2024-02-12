package com.san_online_test.weatherapp.presentation.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.san_online_test.navigation.DestinationModel
import com.san_online_test.navigation.WeatherAppDestination
import com.san_online_test.ui.R
import com.san_online_test.weatherapp.presentation.profile.ProfileScreen

const val PROFILE_ROUTE = "profile"

fun NavGraphBuilder.profile(){
    composable(ProfileDestination.route){
        ProfileScreen()
    }
}

object ProfileDestination: WeatherAppDestination {
    override val route = PROFILE_ROUTE
}

object ProfileTopLevelDestination: DestinationModel {
    override val iconId = R.drawable.ic_person
    override val titleId = R.string.profile_screen_bottom
    override val route = ProfileDestination.route
}