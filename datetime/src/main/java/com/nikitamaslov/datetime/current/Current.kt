package com.nikitamaslov.datetime.current

import com.nikitamaslov.datetime.calendar.Calendar
import com.nikitamaslov.datetime.calendar.getDateTime
import com.nikitamaslov.datetime.calendar.now
import com.nikitamaslov.datetime.model.DateTime

fun currentMillis(): Long = System.currentTimeMillis()

fun currentDateTime(): DateTime = Calendar.now().getDateTime()