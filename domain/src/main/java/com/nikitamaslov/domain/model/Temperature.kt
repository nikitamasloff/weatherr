package com.nikitamaslov.domain.model

data class Temperature(
    val value: Int,
    val unit: Unit
) {

    enum class Unit {
        CELSIUS,
        FAHRENHEIT
    }
}