package com.nikitamaslov.presentation.mapper

import com.nikitamaslov.domain.model.Location
import com.nikitamaslov.presentation.model.LocationFigure

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