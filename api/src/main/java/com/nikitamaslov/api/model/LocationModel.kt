package com.nikitamaslov.api.model

internal data class LocationModel(
    val coordinates: Coordinates,
    val descriptor: Descriptor
) {

    data class Coordinates(
        val latitude: Double,
        val longitude: Double
    )

    data class Descriptor(
        val city: String,
        val country: String
    )
}