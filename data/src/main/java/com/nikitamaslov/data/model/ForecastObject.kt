package com.nikitamaslov.data.model

data class ForecastObject(
    val currentWeather: WeatherObject,
    val futureWeather: Set<WeatherObject>,
    val metadata: Metadata
) {

    data class Metadata(
        val location: LocationObject,
        val temperatureUnit: TemperatureObject.Unit,
        val dateTime: DateTimeObject
    )
}