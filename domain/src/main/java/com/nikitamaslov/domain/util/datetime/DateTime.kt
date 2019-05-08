package com.nikitamaslov.domain.util.datetime

import com.nikitamaslov.datetime.calendar.Calendar
import com.nikitamaslov.datetime.calendar.inMillis
import com.nikitamaslov.datetime.calendar.set
import com.nikitamaslov.domain.model.DateTime
import com.nikitamaslov.domain.model.Duration
import kotlin.math.abs
import com.nikitamaslov.datetime.current.currentDateTime as utilCurrentDateTime
import com.nikitamaslov.datetime.model.DateTime as UtilDateTime

private fun UtilDateTime.mapToDateTime() = DateTime(year, month, dayOfMonth, hour, minute)

private fun DateTime.mapToUtilDateTime() = UtilDateTime(year, month, dayOfMonth, hour, minute)

internal fun currentDateTime(): DateTime = utilCurrentDateTime().mapToDateTime()

internal operator fun DateTime.minus(other: DateTime): Duration {
    val calendar = Calendar.instance()

    calendar.set(this.mapToUtilDateTime())
    val thisMillis = calendar.inMillis()

    calendar.set(other.mapToUtilDateTime())
    val otherMillis = calendar.inMillis()

    val diff = abs(thisMillis - otherMillis)
    return Duration(diff)
}

internal operator fun Duration.compareTo(other: Duration): Int = this.millis.compareTo(other.millis)