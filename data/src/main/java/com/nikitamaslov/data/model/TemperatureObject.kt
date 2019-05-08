package com.nikitamaslov.data.model

data class TemperatureObject(
    val value: Int,
    val unit: Unit
) {

    enum class Unit {
        CELSIUS,
        FAHRENHEIT
    }
}