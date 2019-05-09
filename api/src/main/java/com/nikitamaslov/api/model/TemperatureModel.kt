package com.nikitamaslov.api.model

internal data class TemperatureModel(
    val value: Int,
    val unit: Unit
) {

    enum class Unit {
        CELSIUS,
        FAHRENHEIT
    }
}