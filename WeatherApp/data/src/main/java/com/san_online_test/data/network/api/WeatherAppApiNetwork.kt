package com.san_online_test.data.network.api

import android.util.Log
import com.san_online_test.data.location.DefaultLocationTracker
import com.san_online_test.data.network.model.WeatherNetwork
import com.san_online_test.domain.model.Location
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.util.Date

class WeatherAppApiNetwork(
    private val moshi: Moshi,
    private val defaultLocationTracker: DefaultLocationTracker,
): WeatherAppApi {
    override suspend fun createApiWithLocation(defaultLocationTracker: DefaultLocationTracker): String {
        val latitude = defaultLocationTracker.getCurrentLocation()?.latitude
        val longitude = defaultLocationTracker.getCurrentLocation()?.longitude
        return if (latitude == null && longitude == null) "http://api.openweathermap.org/data/2.5/" +
                "forecast?lat=55.7522&lon=37.6156&lang=ru&appid=f6f4dc2da8092cb1c8b18c09dfd1e77f&units=metric"
        else "http://api.openweathermap.org/data/2.5/forecast?lat=${latitude}&lon=${longitude}&lang=ru&appid=f6f4dc2da8092cb1c8b18c09dfd1e77f&units=metric"
    }
    //"http://api.openweathermap.org/data/2.5/forecast?lat=55.7522&lon=37.6156&appid=f6f4dc2da8092cb1c8b18c09dfd1e77f&units=metric"
    override suspend fun getWeather(): WeatherNetwork = withContext(Dispatchers.IO){
        val url = URL(createApiWithLocation(defaultLocationTracker))
        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(WeatherNetwork::class.java)
        val response = jsonAdapter.fromJson(json) as WeatherNetwork

        Log.d("AAA", response.toString())
        response
    }


}