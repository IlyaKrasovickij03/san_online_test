package com.san_online_test.data.location

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager

import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.san_online_test.data.storage.LocalStorage
import com.san_online_test.domain.location.LocationTracker
import com.san_online_test.domain.model.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine


class DefaultLocationTracker(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val application: Application,
    private val localStorage: LocalStorage,
) : LocationTracker {

    val locationScope = CoroutineScope(context = Dispatchers.IO)
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager

        val isGpsEnabled = locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!isGpsEnabled && !(hasAccessCoarseLocationPermission || hasAccessFineLocationPermission)) {
            Log.d("AAA", "зашел в куда не надо")
            return null
        }

//        if (!isGpsEnabled) return Location()
        if (!isGpsEnabled) {
            Log.d("AAA", localStorage.getLastUserLocation().toString())
            return localStorage.getLastUserLocation()
        }
        return suspendCancellableCoroutine { cont ->
                val locationRequest = LocationRequest.create().apply {
                    interval = 10000
                    fastestInterval = 5000
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }
//                Log.d("AAA", "LOCATION")

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        locationScope.launch {
                            Log.d("latitude", p0.lastLocation!!.latitude.toString())
                            Log.d("longitude", p0.lastLocation!!.longitude.toString())
                            localStorage.saveLastUserLocation(Location(
                                latitude = p0.lastLocation!!.latitude,
                                longitude = p0.lastLocation!!.longitude
                            ))
                        }
                        for (location in p0.locations) {
                            if (location != null && cont.isActive) {

                                cont.resume(
                                    Location(
                                        latitude = location.latitude,
                                        longitude = location.longitude
                                    )
                                ) {}  // Resume coroutine with location result
                                break
                            } else if (cont.isActive) {
                                cont.resume(null) {} // Resume coroutine with null location result
                                break
                            }
                        }
                    }
                }

                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }
    }
}
