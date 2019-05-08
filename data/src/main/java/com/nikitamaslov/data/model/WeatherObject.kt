package com.nikitamaslov.data.model

data class WeatherObject(
    val dateTime: DateTimeObject,
    val type: Type,
    val temperature: TemperatureObject,
    val descriptor: Descriptor
) {

    enum class Type {
        CLEAR,
        CLOUDS,
        RAIN,
        SNOW,
        DRIZZLE,
        THUNDERSTORM,
        ATMOSPHERE
    }

    data class Descriptor(val description: String)
}