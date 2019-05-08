package com.nikitamaslov.storage.mapper

import com.nikitamaslov.data.model.*
import com.nikitamaslov.storage.model.common.WeatherEntity
import com.nikitamaslov.storage.model.def.TemperatureUnitEntity
import com.nikitamaslov.storage.model.def.TemperatureUnitEntityDef
import com.nikitamaslov.storage.model.def.WeatherTypeEntity
import com.nikitamaslov.storage.model.def.WeatherTypeEntityDef
import com.nikitamaslov.storage.model.single.SingleForecastEntity
import com.nikitamaslov.storage.model.single.SingleLocationEntity
import com.nikitamaslov.storage.model.single.SingleTemperatureUnitWrapperEntity

private fun defError(): Nothing = throw IllegalStateException()

internal fun TemperatureUnitEntity.mapToTemperatureUnitObject(): TemperatureObject.Unit =
    when (this) {
        TemperatureUnitEntityDef.CELSIUS -> TemperatureObject.Unit.CELSIUS
        TemperatureUnitEntityDef.FAHRENHEIT -> TemperatureObject.Unit.FAHRENHEIT
        else -> defError()
    }

internal fun TemperatureObject.Unit.mapToTemperatureUnitEntity(): TemperatureUnitEntity =
    when (this) {
        TemperatureObject.Unit.CELSIUS -> TemperatureUnitEntityDef.CELSIUS
        TemperatureObject.Unit.FAHRENHEIT -> TemperatureUnitEntityDef.FAHRENHEIT
    }

internal fun TemperatureObject.Unit.mapToSingleTemperatureUnitWrapperEntity() =
    SingleTemperatureUnitWrapperEntity(value = this.mapToTemperatureUnitEntity())

internal fun SingleTemperatureUnitWrapperEntity.mapToTemperatureUnitObject(): TemperatureObject.Unit =
    value.mapToTemperatureUnitObject()

internal fun LocationObject.mapToSingleLocationEntity() =
    SingleLocationEntity(
        latitude = coordinates.latitude,
        longitude = coordinates.longitude,
        city = descriptor.city,
        country = descriptor.country
    )

internal fun SingleLocationEntity.mapToLocationObject() =
    LocationObject(
        descriptor = LocationObject.Descriptor(city, country),
        coordinates = LocationObject.Coordinates(latitude, longitude)
    )

internal fun WeatherTypeEntity.mapToWeatherTypeObject(): WeatherObject.Type =
    when (this) {
        WeatherTypeEntityDef.CLEAR -> WeatherObject.Type.CLEAR
        WeatherTypeEntityDef.CLOUDS -> WeatherObject.Type.CLOUDS
        WeatherTypeEntityDef.RAIN -> WeatherObject.Type.RAIN
        WeatherTypeEntityDef.SNOW -> WeatherObject.Type.SNOW
        WeatherTypeEntityDef.DRIZZLE -> WeatherObject.Type.DRIZZLE
        WeatherTypeEntityDef.THUNDERSTORM -> WeatherObject.Type.THUNDERSTORM
        WeatherTypeEntityDef.ATMOSPHERE -> WeatherObject.Type.ATMOSPHERE
        else -> defError()
    }

internal fun WeatherObject.Type.mapToWeatherTypeEntity(): WeatherTypeEntity =
    when (this) {
        WeatherObject.Type.CLEAR -> WeatherTypeEntityDef.CLEAR
        WeatherObject.Type.CLOUDS -> WeatherTypeEntityDef.CLOUDS
        WeatherObject.Type.RAIN -> WeatherTypeEntityDef.RAIN
        WeatherObject.Type.SNOW -> WeatherTypeEntityDef.SNOW
        WeatherObject.Type.DRIZZLE -> WeatherTypeEntityDef.DRIZZLE
        WeatherObject.Type.THUNDERSTORM -> WeatherTypeEntityDef.THUNDERSTORM
        WeatherObject.Type.ATMOSPHERE -> WeatherTypeEntityDef.ATMOSPHERE
    }

internal fun WeatherObject.mapToWeatherEntity() =
    WeatherEntity(
        year = dateTime.year,
        month = dateTime.month,
        dayOfMonth = dateTime.dayOfMonth,
        hour = dateTime.hour,
        minute = dateTime.minute,
        type = type.mapToWeatherTypeEntity(),
        temperature = temperature.value,
        temperatureUnit = temperature.unit.mapToTemperatureUnitEntity(),
        description = descriptor.description
    )

internal fun WeatherEntity.mapToWeatherObject() =
    WeatherObject(
        dateTime = DateTimeObject(year, month, dayOfMonth, hour, minute),
        type = type.mapToWeatherTypeObject(),
        temperature = TemperatureObject(temperature, temperatureUnit.mapToTemperatureUnitObject()),
        descriptor = WeatherObject.Descriptor(description)
    )

internal fun ForecastObject.mapToSingleForecastEntity() =
    SingleForecastEntity(
        currentWeather = currentWeather.mapToWeatherEntity(),
        futureWeather = futureWeather.map { it.mapToWeatherEntity() }.toSet(),
        latitude = metadata.location.coordinates.latitude,
        longitude = metadata.location.coordinates.longitude,
        city = metadata.location.descriptor.city,
        country = metadata.location.descriptor.country,
        temperatureUnit = metadata.temperatureUnit.mapToTemperatureUnitEntity(),
        year = metadata.dateTime.year,
        month = metadata.dateTime.month,
        dayOfMonth = metadata.dateTime.dayOfMonth,
        hour = metadata.dateTime.hour,
        minute = metadata.dateTime.minute
    )

internal fun SingleForecastEntity.mapToForecastObject() =
    ForecastObject(
        currentWeather = currentWeather.mapToWeatherObject(),
        futureWeather = futureWeather.map { it.mapToWeatherObject() }.toSet(),
        metadata = ForecastObject.Metadata(
            location = LocationObject(
                descriptor = LocationObject.Descriptor(city, country),
                coordinates = LocationObject.Coordinates(latitude, longitude)
            ),
            temperatureUnit = temperatureUnit.mapToTemperatureUnitObject(),
            dateTime = DateTimeObject(year, month, dayOfMonth, hour, minute)
        )
    )