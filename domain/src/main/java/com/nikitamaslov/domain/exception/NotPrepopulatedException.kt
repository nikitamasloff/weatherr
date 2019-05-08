package com.nikitamaslov.domain.exception

internal class NotPrepopulatedException(message: String? = null) : IllegalStateException(message)

internal fun notPrepopulatedError(subject: Any): Nothing =
    throw NotPrepopulatedException("$subject has not been prepopulated")