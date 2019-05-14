package com.nikitamaslov.ui.model

internal data class LocationForm(
    val descriptor: Descriptor,
    val coordinates: Coordinates
) {

    data class Descriptor(val city: String, val country: String)
    data class Coordinates(val latitude: Double, val longitude: Double)
}