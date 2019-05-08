package com.nikitamaslov.domain.model

data class Forecast(
    val currentWeather: Weather,
    val futureWeather: Set<Weather>,
    val metadata: Metadata
) {

    data class Metadata(
        val location: Location,
        val temperatureUnit: Temperature.Unit,
        val dateTime: DateTime
    )
}