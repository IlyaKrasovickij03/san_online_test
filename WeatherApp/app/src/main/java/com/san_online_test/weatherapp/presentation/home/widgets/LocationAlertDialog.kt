package com.san_online_test.weatherapp.presentation.home.widgets

import android.Manifest
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.san_online_test.ui.R
import com.san_online_test.ui.design.theme.WeatherAppTheme

@Composable
fun LocationAlertDialog(launcher: ManagedActivityResultLauncher<String, Boolean>) {
    var showAlertDialog by remember { mutableStateOf(true) }
    if (showAlertDialog){
        AlertDialog(onDismissRequest = { showAlertDialog = false },
            title = { Text(text = stringResource(id = R.string.alert_location_title)) },
            text = { Text(text = stringResource(id = R.string.alert_location_text)) },
            confirmButton = {
                Button(onClick = { launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }) {
                    Text(text = stringResource(id = R.string.alert_location_confirm_button))
                }
            },
            dismissButton = {
                Button(onClick = { showAlertDialog = false }) {
                    Text(text = stringResource(id = R.string.alert_location_dismiss_button))
                }
            })
    } else {
        HomeScreenFailed()
    }
}

@Preview
@Composable
fun LocationAlertDialogPreview() {
    WeatherAppTheme {
        LocationAlertDialog(
            launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()
            ) {})
    }
}