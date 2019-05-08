package com.nikitamaslov.datetime.validator

internal class ValidationException(message: String? = null) : IllegalStateException(message)

internal fun validationError(message: String? = null): Nothing = throw ValidationException(message)