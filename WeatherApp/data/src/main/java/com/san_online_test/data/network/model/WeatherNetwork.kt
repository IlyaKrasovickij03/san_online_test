package com.san_online_test.data.network.model

import com.squareup.moshi.Json
data class WeatherNetwork(
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<WeatherDetails>,
    val city: City,
)

data class WeatherDetails(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val snow: Snow?,
    val sys: Sys,
    @Json(name = "dt_txt")
    val dtTxt: String,
)

data class Main(
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    @Json(name = "temp_min")
    val tempMin: Double,
    @Json(name = "temp_max")
    val tempMax: Double,
    val pressure: Long,
    @Json(name = "sea_level")
    val seaLevel: Long,
    @Json(name = "grnd_level")
    val grndLevel: Long,
    val humidity: Long,
    @Json(name = "temp_kf")
    val tempKf: Double,
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)

data class Clouds(
    val all: Long,
)

data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double,
)

data class Snow(
    @Json(name = "3h")
    val n3h: Double,
)

data class Sys(
    val pod: String,
)

data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long,
)

data class Coord(
    val lat: Double,
    val lon: Double,
)


// @Json(name = "dt") val iconUrl: Int,
