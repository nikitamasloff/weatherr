package com.nikitamaslov.domain.model

data class Location(
    val descriptor: Descriptor,
    val coordinates: Coordinates
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
