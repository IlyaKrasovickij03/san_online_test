package com.san_online_test.data.network.api

import android.util.Log
import com.san_online_test.data.location.DefaultLocationTracker
import com.san_online_test.data.network.model.WeatherNetwork
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class WeatherAppApiNetwork(
    private val moshi: Moshi,
    private val defaultLocationTracker: DefaultLocationTracker
): WeatherAppApi {
    override suspend fun createApiWithLocation(defaultLocationTracker: DefaultLocationTracker): String {
        val latitude = defaultLocationTracker.getCurrentLocation()?.latitude
        val longitude = defaultLocationTracker.getCurrentLocation()?.longitude
        return "http://api.openweathermap.org/data/2.5/forecast?lat=${latitude}&lon=${longitude}&appid=f6f4dc2da8092cb1c8b18c09dfd1e77f"
    }

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