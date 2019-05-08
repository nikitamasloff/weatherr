package com.nikitamaslov.datetime.validator

internal fun validate(statement: Boolean) {
    if (!statement) validationError()
}

internal fun validate(statement: Boolean, message: Any) {
    if (!statement) validationError(message.toString())
}

internal fun validate(statement: Boolean, lazyMessage: () -> Any) {
    if (!statement) validationError(lazyMessage().toString())
}

internal fun validate(lazyStatement: () -> Boolean) {
    if (!lazyStatement()) validationError()
}

internal fun validate(lazyStatement: () -> Boolean, message: Any) {
    if (!lazyStatement()) validationError(message.toString())
}

internal fun validate(lazyStatement: () -> Boolean, lazyMessage: () -> Any) {
    if (!lazyStatement()) validationError(lazyMessage().toString())
}