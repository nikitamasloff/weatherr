package com.nikitamaslov.api.mapper

import com.nikitamaslov.api.model.*
import com.nikitamaslov.data.model.*

internal fun DurationModel.mapToDurationObject() = DurationObject(millis)

internal fun LocationObject.Coordinates.mapToCoordinatesModel() =
    LocationModel.Coordinates(latitude, longitude)

internal fun LocationObject.Descriptor.mapToLocationDescriptorModel() =
    LocationModel.Descriptor(city, country)

internal fun LocationObject.mapToLocationModel() =
    LocationModel(
        coordinates = coordinates.mapToCoordinatesModel(),
        descriptor = descriptor.mapToLocationDescriptorModel()
    )

internal fun LocationModel.Descriptor.mapToLocationDescriptorObject() =
    LocationObject.Descriptor(city, country)

internal fun LocationModel.Coordinates.mapToCoordinatesObject() =
    LocationObject.Coordinates(latitude, longitude)

internal fun LocationModel.mapToLocationObject() =
    LocationObject(
        descriptor = descriptor.mapToLocationDescriptorObject(),
        coordinates = coordinates.mapToCoordinatesObject()
    )

internal fun TemperatureObject.Unit.mapToTemperatureUnitModel(): TemperatureModel.Unit =
    when (this) {
        TemperatureObject.Unit.CELSIUS -> TemperatureModel.Unit.CELSIUS
        TemperatureObject.Unit.FAHRENHEIT -> TemperatureModel.Unit.FAHRENHEIT
    }

internal fun TemperatureModel.Unit.mapToTemperatureUnitObject(): TemperatureObject.Unit =
    when (this) {
        TemperatureModel.Unit.CELSIUS -> TemperatureObject.Unit.CELSIUS
        TemperatureModel.Unit.FAHRENHEIT -> TemperatureObject.Unit.FAHRENHEIT
    }

internal fun TemperatureModel.mapToTemperatureObject() =
    TemperatureObject(
        value = value,
        unit = unit.mapToTemperatureUnitObject()
    )

internal fun DateTimeModel.mapToDateTimeObject() =
    DateTimeObject(year, month, dayOfMonth, hour, minute)

internal fun WeatherModel.Descriptor.mapToWeatherDescriptorObject() =
    WeatherObject.Descriptor(description)

internal fun WeatherModel.Type.mapToWeatherTypeObject(): WeatherObject.Type =
    when (this) {
        WeatherModel.Type.CLEAR -> WeatherObject.Type.CLEAR
        WeatherModel.Type.CLOUDS -> WeatherObject.Type.CLOUDS
        WeatherModel.Type.RAIN -> WeatherObject.Type.RAIN
        WeatherModel.Type.SNOW -> WeatherObject.Type.SNOW
        WeatherModel.Type.DRIZZLE -> WeatherObject.Type.DRIZZLE
        WeatherModel.Type.THUNDERSTORM -> WeatherObject.Type.THUNDERSTORM
        WeatherModel.Type.ATMOSPHERE -> WeatherObject.Type.ATMOSPHERE
    }

internal fun WeatherModel.mapToWeatherObject() =
    WeatherObject(
        dateTime = dateTime.mapToDateTimeObject(),
        type = type.mapToWeatherTypeObject(),
        temperature = temperature.mapToTemperatureObject(),
        descriptor = descriptor.mapToWeatherDescriptorObject()
    )

internal fun ForecastModel.Metadata.mapToForecastMetadataObject() =
    ForecastObject.Metadata(
        location = location.mapToLocationObject(),
        temperatureUnit = temperatureUnit.mapToTemperatureUnitObject(),
        dateTime = dateTime.mapToDateTimeObject()
    )

internal fun ForecastModel.mapToForecastObject() =
    ForecastObject(
        currentWeather = currentWeather.mapToWeatherObject(),
        futureWeather = futureWeather.map(WeatherModel::mapToWeatherObject).toSet(),
        metadata = metadata.mapToForecastMetadataObject()
    )