package com.nikitamaslov.presentation.model

data class WeatherFigure(
    val dateTime: DateTimeFigure,
    val type: Type,
    val temperature: TemperatureFigure,
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