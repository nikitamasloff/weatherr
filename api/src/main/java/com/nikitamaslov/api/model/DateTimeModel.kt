package com.nikitamaslov.api.model

internal data class DateTimeModel(
    val year: Int,
    val month: Int,
    val dayOfMonth: Int,
    val hour: Int,
    val minute: Int
)