package com.nikitamaslov.domain.exception

class LocationNotSpecifiedException(message: String? = null) : Exception(message)

fun locationNotSpecifiedError(): Nothing = throw LocationNotSpecifiedException()