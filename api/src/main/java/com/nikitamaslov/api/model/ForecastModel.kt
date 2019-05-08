package com.nikitamaslov.api.model

internal data class ForecastModel(
    val currentWeather: WeatherModel,
    val futureWeather: Set<WeatherModel>,
    val metadata: Metadata
) {

    data class Metadata(
        val location: LocationModel,
        val temperatureUnit: TemperatureModel.Unit,
        val dateTime: DateTimeModel
    )
}