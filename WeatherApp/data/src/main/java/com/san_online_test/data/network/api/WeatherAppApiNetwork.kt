package com.san_online_test.data.network.api

import android.util.Log
import com.san_online_test.data.network.model.WeatherNetwork
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class WeatherAppApiNetwork(
    private val apiUrl: String,
    private val moshi: Moshi
): WeatherAppApi {
    override suspend fun getWeather(): WeatherNetwork = withContext(Dispatchers.IO){
        val url = URL(apiUrl)
        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(WeatherNetwork::class.java)
        val response = jsonAdapter.fromJson(json) as WeatherNetwork

        Log.d("AAA", response.toString())
        response
    }
}