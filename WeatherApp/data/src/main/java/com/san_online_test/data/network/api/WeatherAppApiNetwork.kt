package com.san_online_test.data.network.api

import android.content.Context
import android.util.Log
import com.san_online_test.data.location.DefaultLocationTracker
import com.san_online_test.data.network.model.WeatherNetwork
import com.san_online_test.data.network_monitor.NetworkMonitor
import com.san_online_test.data.storage.LocalStorage
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume

class WeatherAppApiNetwork(
    private val moshi: Moshi,
    private val defaultLocationTracker: DefaultLocationTracker,
    private val context: Context,
    private val localStorage: LocalStorage
) : WeatherAppApi {
    private val networkMonitor = NetworkMonitor()
    private val localScope = CoroutineScope(context = Dispatchers.IO)


    override suspend fun createApiWithLocation(defaultLocationTracker: DefaultLocationTracker): String {
        val latitude = defaultLocationTracker.getCurrentLocation()?.latitude
        val longitude = defaultLocationTracker.getCurrentLocation()?.longitude
        return if (latitude == null && longitude == null) "http://api.openweathermap.org/data/2.5/" + "forecast?lat=55.7522&lon=37.6156&lang=ru&appid=f6f4dc2da8092cb1c8b18c09dfd1e77f&units=metric"
        else "http://api.openweathermap.org/data/2.5/forecast?lat=${latitude}&lon=${longitude}&lang=ru&appid=f6f4dc2da8092cb1c8b18c09dfd1e77f&units=metric"
    }


    override suspend fun getWeather(): WeatherNetwork = suspendCancellableCoroutine { continuation ->
        if (networkMonitor.isOnline(context)) {
            localScope.launch {
                val url = URL(createApiWithLocation(defaultLocationTracker))
                val con = withContext(Dispatchers.IO) {
                    url.openConnection()
                } as HttpURLConnection
                val json = con.inputStream.bufferedReader().use { it.readText() }
                localStorage.saveLastDataFromNetwork(json)
                val jsonAdapter = moshi.adapter(WeatherNetwork::class.java)
                val response = jsonAdapter.fromJson(json) as WeatherNetwork
                try {
                    if (!continuation.isCompleted) {
                        continuation.resume(response)
                    }
                } catch (e: IllegalStateException) {
                    // Обработка исключения
                }
            }
        } else {
            localScope.launch {
                Log.d("NETWORK_RESULT", "MONITOR NULL")
                val json = localStorage.getLastDataFromNetwork()
                val jsonAdapter = moshi.adapter(WeatherNetwork::class.java)
                val response = jsonAdapter.fromJson(json) as WeatherNetwork
                try {
                    if (!continuation.isCompleted) {
                        continuation.resume(response)
                    }
                } catch (e: IllegalStateException) {
                    // Обработка исключения
                }
            }
        }
    }
}



