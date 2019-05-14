package com.nikitamaslov.ui.mapper

import com.nikitamaslov.presentation.model.*
import com.nikitamaslov.ui.model.*

internal fun TemperatureFigure.Unit.mapToTemperatureUnitForm(): TemperatureForm.Unit =
    when (this) {
        TemperatureFigure.Unit.CELSIUS -> TemperatureForm.Unit.CELSIUS
        TemperatureFigure.Unit.FAHRENHEIT -> TemperatureForm.Unit.FAHRENHEIT
    }

internal fun TemperatureForm.Unit.mapToTemperatureUnitFigure(): TemperatureFigure.Unit =
    when (this) {
        TemperatureForm.Unit.CELSIUS -> TemperatureFigure.Unit.CELSIUS
        TemperatureForm.Unit.FAHRENHEIT -> TemperatureFigure.Unit.FAHRENHEIT
    }

internal fun TemperatureFigure.mapToTemperatureForm() =
    TemperatureForm(
        value = value,
        unit = unit.mapToTemperatureUnitForm()
    )

internal fun DateTimeFigure.mapToDateTimeForm() =
    DateTimeForm(year, month, dayOfMonth, hour, minute)

internal fun WeatherFigure.Descriptor.mapToWeatherDescriptorForm() =
    WeatherForm.Descriptor(description)

internal fun WeatherFigure.Type.mapToWeatherFormType(): WeatherForm.Type =
    when (this) {
        WeatherFigure.Type.CLEAR -> WeatherForm.Type.CLEAR
        WeatherFigure.Type.CLOUDS -> WeatherForm.Type.CLOUDS
        WeatherFigure.Type.RAIN -> WeatherForm.Type.RAIN
        WeatherFigure.Type.SNOW -> WeatherForm.Type.SNOW
        WeatherFigure.Type.DRIZZLE -> WeatherForm.Type.DRIZZLE
        WeatherFigure.Type.THUNDERSTORM -> WeatherForm.Type.THUNDERSTORM
        WeatherFigure.Type.ATMOSPHERE -> WeatherForm.Type.ATMOSPHERE
    }

internal fun WeatherForm.Type.mapToWeatherTypeFigure(): WeatherFigure.Type =
    when (this) {
        WeatherForm.Type.CLEAR -> WeatherFigure.Type.CLEAR
        WeatherForm.Type.CLOUDS -> WeatherFigure.Type.CLOUDS
        WeatherForm.Type.RAIN -> WeatherFigure.Type.RAIN
        WeatherForm.Type.SNOW -> WeatherFigure.Type.SNOW
        WeatherForm.Type.DRIZZLE -> WeatherFigure.Type.DRIZZLE
        WeatherForm.Type.THUNDERSTORM -> WeatherFigure.Type.THUNDERSTORM
        WeatherForm.Type.ATMOSPHERE -> WeatherFigure.Type.ATMOSPHERE
    }

internal fun WeatherFigure.mapToCurrentWeather() =
    CurrentWeatherForm(
        type = type.mapToWeatherFormType(),
        temperature = temperature.mapToTemperatureForm(),
        descriptor = descriptor.mapToWeatherDescriptorForm()
    )

internal fun WeatherFigure.mapToFutureWeather() =
    FutureWeatherForm(
        dateTime = dateTime.mapToDateTimeForm(),
        type = type.mapToWeatherFormType(),
        temperature = temperature.mapToTemperatureForm(),
        descriptor = descriptor.mapToWeatherDescriptorForm()
    )

internal fun NotificationFigure.mapToNotificationForm(): NotificationForm =
    when (this) {
        NotificationFigure.NETWORK_CONNECTION_PROBLEMS -> NotificationForm.NO_NETWORK_CONNECTION
        NotificationFigure.SERVER_ERROR -> NotificationForm.SERVER_PROBLEMS
        NotificationFigure.LOCATION_NOT_SPECIFIED -> NotificationForm.LOCATION_NOT_SPECIFIED
    }

internal fun LocationFigure.Descriptor.mapToLocationFormDescriptor() =
    LocationForm.Descriptor(city, country)

internal fun LocationForm.Descriptor.mapToLocationFigureDescriptor() =
    LocationFigure.Descriptor(city, country)

internal fun LocationFigure.Coordinates.mapToCoordinatesForm() =
    LocationForm.Coordinates(latitude, longitude)

internal fun LocationForm.Coordinates.mapToCoordinatesFigure() =
    LocationFigure.Coordinates(latitude, longitude)

internal fun LocationFigure.mapToLocationForm() =
    LocationForm(
        descriptor = descriptor.mapToLocationFormDescriptor(),
        coordinates = coordinates.mapToCoordinatesForm()
    )

internal fun LocationForm.mapToLocationFigure() =
    LocationFigure(
        descriptor = descriptor.mapToLocationFigureDescriptor(),
        coordinates = coordinates.mapToCoordinatesFigure()
    )