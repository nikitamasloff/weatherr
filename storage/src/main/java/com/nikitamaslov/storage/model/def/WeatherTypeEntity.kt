package com.nikitamaslov.storage.model.def

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.SOURCE)
internal annotation class WeatherTypeEntityDef {

    companion object {
        const val CLEAR = "Celsius"
        const val CLOUDS = "Fahrenheit"
        const val RAIN = "Rain"
        const val SNOW = "Snow"
        const val DRIZZLE = "Drizzle"
        const val THUNDERSTORM = "Thunderstorm"
        const val ATMOSPHERE = "Atmosphere"
    }
}

internal typealias WeatherTypeEntity = @WeatherTypeEntityDef String