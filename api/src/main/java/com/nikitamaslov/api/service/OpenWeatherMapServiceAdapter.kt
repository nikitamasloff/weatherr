package com.nikitamaslov.api.service

import com.nikitamaslov.api.model.LocationModel
import com.nikitamaslov.api.model.TemperatureModel
import com.nikitamaslov.api.service.response.CurrentWeatherResponse
import com.nikitamaslov.api.service.response.HourlyForecastResponse
import kotlinx.coroutines.Deferred

internal interface OpenWeatherMapServiceAdapter {

    fun fetchHourlyForecastAsync(
        coordinates: LocationModel.Coordinates,
        temperatureUnit: TemperatureModel.Unit
    ): Deferred<HourlyForecastResponse>

    fun fetchCurrentWeatherAsync(
        coordinates: LocationModel.Coordinates,
        temperatureUnit: TemperatureModel.Unit
    ): Deferred<CurrentWeatherResponse>
}

internal class OpenWeatherMapServiceAdapterImpl(
    private val openWeatherMapService: OpenWeatherMapService
) : OpenWeatherMapServiceAdapter {

    override fun fetchHourlyForecastAsync(
        coordinates: LocationModel.Coordinates,
        temperatureUnit: TemperatureModel.Unit
    ): Deferred<HourlyForecastResponse> =
        openWeatherMapService.fetchHourlyForecastAsync(
            latitude = coordinates.latitude,
            longitude = coordinates.longitude,
            unit = temperatureUnit.asQueryParameter()
        )

    override fun fetchCurrentWeatherAsync(
        coordinates: LocationModel.Coordinates,
        temperatureUnit: TemperatureModel.Unit
    ): Deferred<CurrentWeatherResponse> =
        openWeatherMapService.fetchCurrentWeatherAsync(
            latitude = coordinates.latitude,
            longitude = coordinates.longitude,
            unit = temperatureUnit.asQueryParameter()
        )

    private fun TemperatureModel.Unit.asQueryParameter(): String =
        when (this) {
            TemperatureModel.Unit.CELSIUS -> OpenWeatherMapService.CELSIUS_UNIT
            TemperatureModel.Unit.FAHRENHEIT -> OpenWeatherMapService.FAHRENHEIT_UNIT
        }
}