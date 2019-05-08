package com.nikitamaslov.data.mapper

import com.nikitamaslov.data.exception.ApiException
import com.nikitamaslov.data.exception.NetworkConnectionException
import com.nikitamaslov.data.exception.ServerException
import com.nikitamaslov.data.model.*
import com.nikitamaslov.domain.exception.ForecastFetchException
import com.nikitamaslov.domain.model.*

internal fun DurationObject.mapToDuration() = Duration(millis)

internal fun TemperatureObject.Unit.mapToTemperatureUnit(): Temperature.Unit =
    when (this) {
        TemperatureObject.Unit.CELSIUS -> Temperature.Unit.CELSIUS
        TemperatureObject.Unit.FAHRENHEIT -> Temperature.Unit.FAHRENHEIT
    }

internal fun Temperature.Unit.mapToTemperatureUnitObject(): TemperatureObject.Unit =
    when (this) {
        Temperature.Unit.CELSIUS -> TemperatureObject.Unit.CELSIUS
        Temperature.Unit.FAHRENHEIT -> TemperatureObject.Unit.FAHRENHEIT
    }

internal fun TemperatureObject.mapToTemperature() =
    Temperature(
        value = value,
        unit = unit.mapToTemperatureUnit()
    )

internal fun Location.Coordinates.mapToCoordinatesObject() =
    LocationObject.Coordinates(latitude, longitude)

internal fun LocationObject.Coordinates.mapToCoordinates() =
    Location.Coordinates(latitude, longitude)

internal fun Location.Descriptor.mapToDescriptorObject() =
    LocationObject.Descriptor(city, country)

internal fun LocationObject.Descriptor.mapToDescriptor() =
    Location.Descriptor(city, country)

internal fun Location.mapToLocationObject() =
    LocationObject(
        descriptor = descriptor.mapToDescriptorObject(),
        coordinates = coordinates.mapToCoordinatesObject()
    )

internal fun LocationObject.mapToLocation() =
    Location(
        descriptor = descriptor.mapToDescriptor(),
        coordinates = coordinates.mapToCoordinates()
    )

internal fun DateTimeObject.mapToDateTime() = DateTime(year, month, dayOfMonth, hour, minute)

internal fun WeatherObject.Descriptor.mapToDescriptor() = Weather.Descriptor(description)

internal fun WeatherObject.Type.mapToWeatherType(): Weather.Type =
    when (this) {
        WeatherObject.Type.CLEAR -> Weather.Type.CLEAR
        WeatherObject.Type.CLOUDS -> Weather.Type.CLOUDS
        WeatherObject.Type.RAIN -> Weather.Type.RAIN
        WeatherObject.Type.SNOW -> Weather.Type.SNOW
        WeatherObject.Type.DRIZZLE -> Weather.Type.DRIZZLE
        WeatherObject.Type.THUNDERSTORM -> Weather.Type.THUNDERSTORM
        WeatherObject.Type.ATMOSPHERE -> Weather.Type.ATMOSPHERE
    }

internal fun WeatherObject.mapToWeather() =
    Weather(
        dateTime = dateTime.mapToDateTime(),
        type = type.mapToWeatherType(),
        descriptor = descriptor.mapToDescriptor(),
        temperature = temperature.mapToTemperature()
    )

internal fun ForecastObject.Metadata.mapToForecastMetadata() =
    Forecast.Metadata(
        location = location.mapToLocation(),
        temperatureUnit = temperatureUnit.mapToTemperatureUnit(),
        dateTime = dateTime.mapToDateTime()
    )

internal fun ForecastObject.mapToForecast() =
    Forecast(
        currentWeather = currentWeather.mapToWeather(),
        futureWeather = futureWeather.map(WeatherObject::mapToWeather).toSet(),
        metadata = metadata.mapToForecastMetadata()
    )

internal fun ApiException.mapToForecastFetchException(): ForecastFetchException =
    when (this) {
        is ServerException -> ForecastFetchException.api(message)
        is NetworkConnectionException -> ForecastFetchException.networkConnection(message)
    }