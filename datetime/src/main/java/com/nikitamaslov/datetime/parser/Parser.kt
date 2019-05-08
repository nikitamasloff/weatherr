package com.nikitamaslov.datetime.parser

import com.nikitamaslov.datetime.calendar.Calendar
import com.nikitamaslov.datetime.calendar.fromMillis
import com.nikitamaslov.datetime.calendar.getDateTime
import com.nikitamaslov.datetime.model.DateTime
import java.text.SimpleDateFormat
import java.util.Date as UtilDate
import java.util.Locale as UtilLocale

object DateTimeParser {

    fun parse(src: String, pattern: ParsePattern): DateTime {
        val utilDate: UtilDate
        try {
            val formatter = SimpleDateFormat(pattern.value, UtilLocale.getDefault())
            utilDate = formatter.parse(src)
        } catch (e: Exception) {
            throw ParseException()
        }
        val calendar = Calendar.fromMillis(utilDate.time)
        return calendar.getDateTime()
    }
}

fun parseDateTime(src: String, pattern: ParsePattern): DateTime = DateTimeParser.parse(src, pattern)