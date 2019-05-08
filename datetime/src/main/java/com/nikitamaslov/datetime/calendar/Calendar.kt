package com.nikitamaslov.datetime.calendar

import com.nikitamaslov.datetime.model.DateTime
import com.nikitamaslov.datetime.model.Month
import com.nikitamaslov.datetime.model.fromIndex
import com.nikitamaslov.datetime.validator.*
import java.util.Calendar as UtilCalendar
import java.util.Date as UtilDate
import java.util.Locale as UtilLocale

class Calendar private constructor() {

    var year: Int = Default.YEAR
        set(value) {
            validateYear(value)
            field = value
        }

    var month: Int = Default.MONTH
        set(value) {
            validateMonth(value)
            field = value
        }

    var dayOfMonth: Int = Default.DAY_OF_MONTH
        set(value) {
            validateDayOfMonth(value)
            field = value
        }

    var hour: Int = Default.HOUR
        set(value) {
            validateHour(value)
            field = value
        }

    var minute: Int = Default.MINUTE
        set(value) {
            validateMinute(value)
            field = value
        }

    operator fun component1(): Int = year
    operator fun component2(): Int = month
    operator fun component3(): Int = dayOfMonth
    operator fun component4(): Int = hour
    operator fun component5(): Int = minute

    private object Default {
        const val YEAR = 0
        const val MONTH = 1
        const val DAY_OF_MONTH = 1
        const val HOUR = 0
        const val MINUTE = 0
    }

    companion object Factory {

        fun instance(): Calendar = Calendar()
    }
}


fun Calendar.set(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    hour: Int,
    minute: Int
) {
    validateDateTime(year, month, dayOfMonth, hour, minute)
    this.year = year
    this.month = month
    this.dayOfMonth = dayOfMonth
    this.hour = hour
    this.minute = minute
}

fun Calendar.setDate(
    year: Int,
    month: Int,
    dayOfMonth: Int
) {
    validateDate(year, month, dayOfMonth)
    this.year = year
    this.month = month
    this.dayOfMonth = dayOfMonth
}

fun Calendar.setTime(
    hour: Int,
    minute: Int
) {
    validateTime(hour, minute)
    this.hour = hour
    this.minute = minute
}


fun Calendar.set(dateTime: DateTime) {
    val (year, month, dayOfMonth, hour, minute) = dateTime
    validateDateTime(year, month, dayOfMonth, hour, minute)
    this.set(year, month, dayOfMonth, hour, minute)
}

fun Calendar.getDateTime(): DateTime = DateTime(year, month, dayOfMonth, hour, minute)

fun Calendar.inMillis(): Long {
    val utilCalendar = UtilCalendar.getInstance()
    utilCalendar.setNormalized(year, month, dayOfMonth, hour, minute)
    return utilCalendar.timeInMillis
}

fun Calendar.setInMillis(millis: Long) {
    validateMillis(millis)
    val utilDate = UtilDate(millis)
    val utilCalendar = UtilCalendar.getInstance()
    utilCalendar.time = utilDate

    val dateTime = utilCalendar.getNormalized()
    this.set(dateTime)
}

fun Calendar.setNow() {
    val now = UtilDate()
    setInMillis(now.time)
}


fun Calendar.Factory.now(): Calendar = instance().apply { setNow() }

fun Calendar.Factory.fromMillis(millis: Long): Calendar = instance().apply { setInMillis(millis) }


var Calendar.monthEnum: Month
    get() = Month.fromIndex(this.month)
    set(value) {
        this.month = value.index
    }


/*
* Extensions for compatibility with java.util.Calendar (UtilCalendar);
*
* Normalize `month` (in java.util.Calendar `month` is indexed from 0).
*/

private fun UtilCalendar.setNormalized(dateTime: DateTime) {
    this[UtilCalendar.YEAR] = dateTime.year
    this[UtilCalendar.MONTH] = dateTime.month - 1
    this[UtilCalendar.DAY_OF_MONTH] = dateTime.dayOfMonth
    this[UtilCalendar.HOUR] = dateTime.hour
    this[UtilCalendar.MINUTE] = dateTime.minute
}

private fun UtilCalendar.setNormalized(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    hour: Int,
    minute: Int
) {
    this[UtilCalendar.YEAR] = year
    this[UtilCalendar.MONTH] = month - 1
    this[UtilCalendar.DAY_OF_MONTH] = dayOfMonth
    this[UtilCalendar.HOUR] = hour
    this[UtilCalendar.MINUTE] = minute
}

private fun UtilCalendar.getNormalized(): DateTime {
    val year = this[UtilCalendar.YEAR]
    val month = this[UtilCalendar.MONTH] + 1
    val dayOfMonth = this[UtilCalendar.DAY_OF_MONTH]
    val hour = this[UtilCalendar.HOUR_OF_DAY]
    val minute = this[UtilCalendar.MINUTE]

    return DateTime(year, month, dayOfMonth, hour, minute)
}