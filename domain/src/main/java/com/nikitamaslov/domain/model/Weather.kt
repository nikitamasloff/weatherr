package com.nikitamaslov.domain.model

data class Weather(
    val dateTime: DateTime,
    val type: Type,
    val descriptor: Descriptor,
    val temperature: Temperature
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