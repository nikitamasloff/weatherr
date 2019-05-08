package com.nikitamaslov.datetime.validator

private object InvalidMessages {
    fun year(year: Int) = "Invalid year = $year"
    fun month(month: Int) = "Invalid month = $month"
    fun dayOfMonth(dayOfMonth: Int) = "Invalid day of month = $dayOfMonth"
    fun hour(hour: Int) = "Invalid hour = $hour"
    fun minute(minute: Int) = "Invalid minute = $minute"
    fun millis(millis: Long) = "Invalid millis = $millis"

    fun date(year: Int, month: Int, dayOfMonth: Int) =
        "Invalid date: year = $year, month = $month, dayOfMonth = $dayOfMonth"
}

private object ValidRanges {
    val year = 0..Int.MAX_VALUE
    val month = 1..12
    val dayOfMonth = 1..31
    val hour = 0..23
    val minute = 0..59
}

internal fun validateYear(year: Int) {
    validate(
        statement = year in ValidRanges.year,
        message = InvalidMessages.year(year)
    )
}

internal fun validateMonth(month: Int) {
    validate(
        statement = month in ValidRanges.month,
        message = InvalidMessages.month(month)
    )
}

internal fun validateDayOfMonth(dayOfMonth: Int) {
    validate(
        statement = dayOfMonth in ValidRanges.dayOfMonth,
        message = InvalidMessages.dayOfMonth(dayOfMonth)
    )
}

internal fun validateHour(hour: Int) {
    validate(
        statement = hour in ValidRanges.hour,
        message = InvalidMessages.hour(hour)
    )
}

internal fun validateMinute(minute: Int) {
    validate(
        statement = minute in ValidRanges.minute,
        message = InvalidMessages.minute(minute)
    )
}

internal fun validateMillis(millis: Long) {
    validate(
        statement = millis >= 0,
        message = InvalidMessages.millis(millis)
    )
}

internal fun validateDate(year: Int, month: Int, dayOfMonth: Int) {
    validateYear(year)
    validateMonth(month)
    validateDayOfMonth(dayOfMonth)

    validate(
        dayOfMonth in 0..when (month) {
            1 -> 31
            2 -> if (year % 4 == 0) 29 else 28
            3 -> 31
            4 -> 30
            5 -> 31
            6 -> 30
            7 -> 31
            8 -> 31
            9 -> 30
            10 -> 31
            11 -> 30
            12 -> 31
            else -> -1
        },
        InvalidMessages.date(year, month, dayOfMonth)
    )
}

internal fun validateTime(hour: Int, minute: Int) {
    validateHour(hour)
    validateMinute(minute)
}

internal fun validateDateTime(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    hour: Int,
    minute: Int
) {
    validateDate(year, month, dayOfMonth)
    validateTime(hour, minute)
}