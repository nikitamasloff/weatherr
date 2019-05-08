package com.nikitamaslov.data.model

data class LocationObject(
    val descriptor: Descriptor,
    val coordinates: Coordinates
) {

    data class Descriptor(val city: String, val country: String)
    data class Coordinates(val latitude: Double, val longitude: Double)
}