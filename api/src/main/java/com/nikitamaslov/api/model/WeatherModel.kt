package com.nikitamaslov.api.model

internal data class WeatherModel(
    val dateTime: DateTimeModel,
    val type: Type,
    val temperature: TemperatureModel,
    val descriptor: Descriptor
) {

    data class Descriptor(val description: String)
    enum class Type {
        CLEAR,
        CLOUDS,
        RAIN,
        SNOW,
        DRIZZLE,
        THUNDERSTORM,
        ATMOSPHERE
    }
}