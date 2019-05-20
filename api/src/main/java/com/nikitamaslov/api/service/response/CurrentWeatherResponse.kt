package com.nikitamaslov.api.service.response

internal data class CurrentWeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
) {
    data class Wind(
        val deg: Int,
        val speed: Double
    )

    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String
    )

    data class Clouds(
        val all: Int
    )

    data class Main(
        val grnd_level: Double,
        val humidity: Int,
        val pressure: Double,
        val sea_level: Double,
        val temp: Double,
        val temp_max: Double,
        val temp_min: Double
    )

    data class Sys(
        val country: String,
        val message: Double,
        val sunrise: Int,
        val sunset: Int
    )

    data class Coord(
        val lat: Double,
        val lon: Double
    )
}
