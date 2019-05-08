package com.nikitamaslov.storage.model.def

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.SOURCE)
internal annotation class TemperatureUnitEntityDef {

    companion object {
        const val CELSIUS = "Celsius"
        const val FAHRENHEIT = "Fahrenheit"
    }
}

internal typealias TemperatureUnitEntity = @TemperatureUnitEntityDef String