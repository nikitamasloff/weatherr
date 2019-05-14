package com.nikitamaslov.ui.model

import com.nikitamaslov.ui.R

internal data class TemperatureForm(
    val value: Int,
    val unit: Unit
) {

    enum class Unit(val resId: Int) {
        CELSIUS(R.string.temp_unit_celsius),
        FAHRENHEIT(R.string.temp_unit_fahrenheit)
    }
}