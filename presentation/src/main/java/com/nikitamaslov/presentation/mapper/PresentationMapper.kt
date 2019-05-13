package com.nikitamaslov.presentation.mapper

import com.nikitamaslov.domain.exception.ApiException
import com.nikitamaslov.domain.exception.ForecastFetchException
import com.nikitamaslov.domain.exception.LocationNotSpecifiedException
import com.nikitamaslov.domain.exception.NetworkConnectionException
import com.nikitamaslov.domain.model.*
import com.nikitamaslov.presentation.model.*

internal fun Location.Descriptor.mapToLocationDescriptorFigure() =
    LocationFigure.Descriptor(city, country)

internal fun Location.Coordinates.mapToCoordinatesFigure() =
    LocationFigure.Coordinates(latitude, longitude)

internal fun Location.mapToLocationFigure() =
    LocationFigure(
        descriptor = descriptor.mapToLocationDescriptorFigure(),
        coordinates = coordinates.mapToCoordinatesFigure()
    )

internal fun LocationFigure.Descriptor.mapToLocationDescriptor() =
    Location.Descriptor(city, country)

internal fun LocationFigure.Coordinates.mapToCoordinates() =
    Location.Coordinates(latitude, longitude)

internal fun LocationFigure.mapToLocation() =
    Location(
        descriptor = descriptor.mapToLocationDescriptor(),
        coordinates = coordinates.mapToCoordinates()
    )

internal fun Temperature.Unit.mapToTemperatureUnitFigure(): TemperatureFigure.Unit =
    when (this) {
        Temperature.Unit.CELSIUS -> TemperatureFigure.Unit.CELSIUS
        Temperature.Unit.FAHRENHEIT -> TemperatureFigure.Unit.FAHRENHEIT
    }

internal fun TemperatureFigure.Unit.mapToTemperatureUnit(): Temperature.Unit =
    when (this) {
        TemperatureFigure.Unit.CELSIUS -> Temperature.Unit.CELSIUS
        TemperatureFigure.Unit.FAHRENHEIT -> Temperature.Unit.FAHRENHEIT
    }

internal fun Temperature.mapToTemperatureFigure() =
    TemperatureFigure(
        value = value,
        unit = unit.mapToTemperatureUnitFigure()
    )

internal fun DateTime.mapToDateTimeFigure() =
    DateTimeFigure(year, month, dayOfMonth, hour, minute)

internal fun Weather.Type.mapToWeatherTypeFigure(): WeatherFigure.Type =
    when (this) {
        Weather.Type.CLEAR -> WeatherFigure.Type.CLEAR
        Weather.Type.CLOUDS -> WeatherFigure.Type.CLOUDS
        Weather.Type.RAIN -> WeatherFigure.Type.RAIN
        Weather.Type.SNOW -> WeatherFigure.Type.SNOW
        Weather.Type.DRIZZLE -> WeatherFigure.Type.DRIZZLE
        Weather.Type.THUNDERSTORM -> WeatherFigure.Type.THUNDERSTORM
        Weather.Type.ATMOSPHERE -> WeatherFigure.Type.ATMOSPHERE
    }

internal fun Weather.Descriptor.mapToWeatherDescriptorFigure() =
    WeatherFigure.Descriptor(description)

internal fun Weather.mapToWeatherFigure() =
    WeatherFigure(
        dateTime = dateTime.mapToDateTimeFigure(),
        type = type.mapToWeatherTypeFigure(),
        temperature = temperature.mapToTemperatureFigure(),
        descriptor = descriptor.mapToWeatherDescriptorFigure()
    )

internal fun Forecast.mapToForecastFigure() =
    ForecastFigure(
        currentWeather = currentWeather.mapToWeatherFigure(),
        futureWeather = futureWeather.map(Weather::mapToWeatherFigure).toSet()
    )

internal fun ForecastFetchException.mapToNotificationFigure(): NotificationFigure =
    when (this) {
        is ApiException -> NotificationFigure.SERVER_ERROR
        is NetworkConnectionException -> NotificationFigure.NETWORK_CONNECTION_PROBLEMS
        else -> throw IllegalStateException()
    }

internal fun LocationNotSpecifiedException.mapToNotificationFigure(): NotificationFigure =
    NotificationFigure.LOCATION_NOT_SPECIFIED