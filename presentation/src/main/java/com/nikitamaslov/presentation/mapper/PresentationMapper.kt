package com.nikitamaslov.presentation.mapper

import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.domain.model.Temperature
import com.nikitamaslov.presentation.model.LocationFigure
import com.nikitamaslov.presentation.model.TemperatureFigure

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