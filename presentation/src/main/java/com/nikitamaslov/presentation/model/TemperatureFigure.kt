package com.nikitamaslov.presentation.model

data class TemperatureFigure(
    val value: Int,
    val unit: Unit
) {

    enum class Unit {
        CELSIUS,
        FAHRENHEIT
    }
}